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

object SparkStreaming03_Window {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("SparkStreaming03_Window").setMaster("local[*]")
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(3))
    //    从指定端口读取数据
    val socketDS: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop201", 9999)
//    设置窗口的大小及滑动的步长，以上两个值都应该是采集周期的整数倍
    val windowDS: DStream[String] = socketDS.window(Seconds(6), Seconds(3))
    val resRDD: DStream[(String, Int)] = windowDS.flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)

    resRDD.print()




    ssc.start()
    ssc.awaitTermination()
  }
}
