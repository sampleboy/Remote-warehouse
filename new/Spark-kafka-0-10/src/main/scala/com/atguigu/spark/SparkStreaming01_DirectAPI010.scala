package com.atguigu.spark





import com.google.inject.spi.StaticInjectionRequest
import org.apache.kafka.clients.consumer.{ConsumerConfig, ConsumerRecord}
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.codehaus.jackson.map.deser.std.StringDeserializer

/**
 * @author: Benchmark boy
 * @date 2022/9/25 22:31
 * @Desc:
 */

object SparkStreaming01_DirectAPI010 {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf对象
    val conf: SparkConf = new SparkConf().setAppName("SparkStreaming01_DirectAPI010").setMaster("local[*]")
    //    创建SparkContext对象
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(3))
    //    构建Kafka参数
    val KafkaParams: Map[String, Object] = Map[String, Object](
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> "hadoop201:9092,hadoop202:9092,hadoop203:9093",
      ConsumerConfig.GROUP_ID_CONFIG -> "bigdata",
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> "org.apache.kafka.common.serialization.StringDeserializer",
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer]
    )
    //    消费kafka数据创建流
    val kafkaDS: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String, String](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](Set("Bigdata0105"), KafkaParams)
    )
//   计算wordcount
    kafkaDS.map((_.value()))
      .flatMap(_.split(" "))
      .map((_,1))
      .reduceByKey(_+_)
      .print()

//    启动任务
    ssc.start()
    ssc.awaitTermination()

  }

}
