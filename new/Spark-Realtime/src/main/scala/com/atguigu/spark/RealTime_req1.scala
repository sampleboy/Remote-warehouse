package com.atguigu.spark

import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

import java.text.SimpleDateFormat
import java.util.Date

/**
 * @author: Benchmark boy
 * @date 2022/9/28 0:13
 * @Desc:
 */

object RealTime_req1 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("RealTime_req1").setMaster("local[*]")
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(3))
    //    ssc.sparkContext.setCheckpointDir("")
    ssc.checkpoint("E:\\ideaproject\\new\\Spark-Realtime\\cp")
    //    创建Kafka参数
    val brokers = "hadoop201:9092,hadoop202:9092,hadoop203:9092"
    val topic = "my-ads-0105"
    val group = "bigdata"
    val deserialization = "org.apache.kafka.common.serialization.StringDeserializer"
    val KafkaParams = Map(
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> brokers,
      ConsumerConfig.GROUP_ID_CONFIG -> group,
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> deserialization,
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> deserialization
    )
    //    创建DS
    val kafkaDS: InputDStream[(String, String)] = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](
      ssc,
      KafkaParams,
      Set(topic)
    )
    //  从kafka的kv值中取出value        msg = 1664296289370,华北,北京,105,4
    val dataDS: DStream[String] = kafkaDS.map(_._2)
    //    将从Kafka拿到的原始数据进行转换  ===>(天_地区_广告，点击次数)
    val mapDS: DStream[(String, Int)] = dataDS.map(
      line => {

        val fields: Array[String] = line.split(",")
        var area = fields(1)
        var adv = fields(4)
        //        获取时间戳
        val timeStamp: Long = fields(0).toLong
        //        根据时间戳创建日期对象
        val day: Date = new Date(timeStamp)
        //        创建simpieDataFormat，对日期对象进行转换
        val sdf: SimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd")
        //        将日期对象转化为字符串
        val dayStr: String = sdf.format(day)

        //        封装元组
        ("dayStr" + "_" + area + "_" + adv, 1)
      }
    )
    //    对每天每地区广告点击数进行聚合处理
    val updataDS: DStream[(String, Int)] = mapDS.updateStateByKey(
      (seq: Seq[Int], buffer: Option[Int]) => {
        Option(seq.sum + buffer.getOrElse(0))
      }
    )
    //    再次对结构进行转换
    val mapDS1: DStream[(String, (String, Int))] = updataDS.map {
      //      (天_地区_广告,1)
      case (k, sum) => {
        val fields: Array[String] = k.split("_")
        //    （天_地区，（广告，sum））
        (fields(0) + "_" + fields(1), (fields(2), sum))
      }
    }
    //    将相同的天和地区放到一个组
    val groupDS: DStream[(String, Iterable[(String, Int)])] = mapDS1.groupByKey()
    //    对分组的数据进行排序


    val resDS: DStream[(String, List[(String, Int)])] = groupDS.mapValues {
      datas => {
        datas.toList.sortBy(-_._2).take(3)
      }
    }
    resDS.print()
    ssc.start()
    ssc.awaitTermination()

  }
}
