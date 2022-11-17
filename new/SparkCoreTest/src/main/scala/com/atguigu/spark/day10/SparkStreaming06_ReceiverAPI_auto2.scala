package com.atguigu.spark.day10

import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author: Benchmark boy
 * @date 2022/9/23 0:02
 * @Desc: 通过DirectAPI连接Kafka数据源,获取数据
 *        自动的维护偏移量,偏移量维护在checkpoint中
 *        修改StreamingContext对象的获取方式,先从检查点获取,如果没有,通过函数创建,会保证数据不丢失
 *        缺点:
 *        1.小文件过多
 *        2.在checkpoint中,只记录最后offset的时间戳,最次启动程序时,会从这个时间到当前时间,把所有周期都执行一次
 *
 */

object SparkStreaming06_ReceiverAPI_auto2 {
  def main(args: Array[String]): Unit = {
    //    创建配置文件对象
    val conf: SparkConf = new SparkConf().setAppName("SparkStreaming04_ReceiverAPI").setMaster("local[*]")
    //    创建Streaming上下文环境对象
    val ssc: StreamingContext = StreamingContext.getActiveOrCreate("E:\\ideaproject\\new\\SparkCoreTest\\cp",
      () => getStreamingContext
    )


    ssc.start()
    ssc.awaitTermination()

  }

  def getStreamingContext(): StreamingContext = {
    //    创建配置文件对象
    val conf: SparkConf = new SparkConf().setAppName("SparkStreaming04_ReceiverAPI").setMaster("local[*]")
    //    创建SparkStreaming上下文环境对象
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(3))


    //    设置检查点目录
    ssc.checkpoint("E:\\ideaproject\\new\\SparkCoreTest\\cp")
    //    准备Kafka参数
    val kafkaParams: Map[String, String] = Map[String, String](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "hadoop201:9092,hadoop202:9092,hadoop203:9092",
      ConsumerConfig.GROUP_ID_CONFIG -> "bigdata"
    )

    val kafaDstream: InputDStream[(String, String)] = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, Set("bigdata-0105")
    )

    //    从kafka中的消息，我们只需要v的部分
    val lineDs: DStream[String] = kafaDstream.map((_._2))
    //    扁平化
    val flatMapDS: DStream[String] = lineDs.flatMap(_.split(" "))
    //    结构转换,进行计数
    val mapDS: DStream[(String, Int)] = flatMapDS.map((_, 1))
    //    聚合
    val reduceDS: DStream[(String, Int)] = mapDS.reduceByKey(_ + _)
    //    打印输出
    reduceDS.print()
    ssc
  }

}
