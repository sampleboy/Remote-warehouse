package com.atguigu.spark.day10

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author: Benchmark boy
 * @date 2022/9/23 0:02
 * @Desc: 通过ReceiverAPI连接KAfKA数据源，获取数据
 */

object SparkStreaming04_ReceiverAPI {
  def main(args: Array[String]): Unit = {
    //    创建配置文件对象
    val conf: SparkConf = new SparkConf().setAppName("SparkStreaming04_ReceiverAPI").setMaster("local[*]")
    //    创建SparkStreaming上下文环境对象
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(3))
    //    连接kafka，创建Dstream
    val kafaDstream: ReceiverInputDStream[(String, String)] = KafkaUtils.createStream(ssc,
      "hadoop201:2181,hadoop202:2181,hadoop203:2181",
      "bigdata",
      Map("bigdata-0105" -> 2)
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


    ssc.start()
    ssc.awaitTermination()

  }

}
