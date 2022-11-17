package com.atguigu.spark

import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

import java.text.SimpleDateFormat
import java.util.Date


object RealTime_req2 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("RealTime_req2").setMaster("local[*]")
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
    //    从Kafka的kv值中取出value
    val dataDS: DStream[String] = kafkaDS.map(_._2)
    //    定义窗口大小以及滑动的步长
    val windowDS: DStream[String] = dataDS.window(Seconds(12), Seconds(3))
    //    对结构进行转换   （adv_id_hhmm,1）
    val mapDS: DStream[(String, Int)] = windowDS.map {
      line => {
        val fields: Array[String] = line.split(",")

        val timestmp: Long = fields(0).toLong
        //        将时间戳转化为日期格式
        val day: Date = new Date(timestmp)
        //        定义simpleDataFormat对日期函数进行转换
        val sdf = new SimpleDateFormat("mm:ss")
        val time = sdf.format(day)
        (fields(4) + "_" + time, 1)
      }
    }
    val resDS: DStream[(String, Int)] = mapDS.reduceByKey(_ + _)
    //    val value: DStream[(String, Int)] = mapDS.reduceByKeyAndWindow(_ + _, _ - _, Seconds(3))
    resDS.print()
    ssc.start()
    ssc.awaitTermination()

  }
}
