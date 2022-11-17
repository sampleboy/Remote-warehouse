package com.atguigu.spark.day10

import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author: Benchmark boy
 * @date 2022/9/23 0:02
 * @Desc: 通过DirectAPI连接Kafka数据源,获取数据
 *        手动维护
 *
 */

object SparkStreaming07_ReceiverAPI_Handle2 {
  def main(args: Array[String]): Unit = {
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
    //      获取上一次消费的位置(偏移量)
    //    实际项目中,为了保证数据精准一致性,我们对数据进行消费处理之后,将偏移量保存在有事务的存储中,例如MySQL
    val fromOffsets: Map[TopicAndPartition, Long] = Map[TopicAndPartition, Long](
      TopicAndPartition("mybak", 0) -> 13L,
      TopicAndPartition("mybak", 1) -> 10L
    )
    //5.使用DirectAPI手动维护offset的方式消费数据
    val kafakDStream: InputDStream[String] = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder, String](
      ssc,
      kafkaParams,
      fromOffsets,
      (m: MessageAndMetadata[String, String]) => m.message())

    //6.定义空集合用于存放数据的offset
    var offsetRanges = Array.empty[OffsetRange]

    //7.将当前消费到的offset进行保存
    kafakDStream.transform { rdd =>
      offsetRanges = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
      rdd
    }.foreachRDD { rdd =>
      for (o <- offsetRanges) {
        println(s"${o.fromOffset}-${o.untilOffset}")
      }
    }

    //8.开启任务
    ssc.start()
    ssc.awaitTermination()


  }
}
