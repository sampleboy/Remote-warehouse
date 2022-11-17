package com.atguigu.spark.day11

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}


/**
 * @author: Benchmark boy
 * @date 2022/9/18 22:54
 * @Desc: 无状态转换-transform
 */

object SparkStreaming01_Transform {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("SparkStreaming01_Transform").setMaster("local[*]")
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(3))
    //    从指定端口读取数据
    val socketDS: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop201", 9999)
    //    转换为RDD操作
    val resRDD: DStream[(String, Int)] = socketDS.transform(
      rdd => {
        val flatMapRDD: RDD[String] = rdd.flatMap(_.split(" "))
        val mapRDD = flatMapRDD.map((_, 1))
        val reduceRDD: RDD[(String, Int)] = mapRDD.reduceByKey(_ + _)
        reduceRDD.sortByKey()
      }
    )

    resRDD.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
