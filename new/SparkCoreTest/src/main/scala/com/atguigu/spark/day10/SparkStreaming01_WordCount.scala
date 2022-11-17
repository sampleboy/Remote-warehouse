package com.atguigu.spark.day10

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 * @author: Benchmark boy
 * @date 2022/9/18 22:54
 * @Desc:
 */

object SparkStreaming01_WordCount {
  def main(args: Array[String]): Unit = {
    //    创建配置文件对象
    val conf: SparkConf = new SparkConf().setAppName("SparkStreaming_WordCount").setMaster("local[*]")
    //    创建Spark Context程序执行入口对象
    val scc: StreamingContext = new StreamingContext(conf, Seconds(3))
    //    从指定端口获取数据
    val socketDS: ReceiverInputDStream[String] = scc.socketTextStream("hadoop201", 9999)
    //    扁平化
    val flatmapDS: DStream[String] = socketDS.flatMap(_.split(" "))
    //    结构转换，计数
    val mapDS: DStream[(String, Int)] = flatmapDS.map((_, 1))
    //    聚合
    val reduceDS: DStream[(String, Int)] = mapDS.reduceByKey(_ + _)
    //    打印输出
    reduceDS.print()
    //    启动采集器
    scc.start()
    //    等待采集结束后，终止程序
    scc.awaitTermination()


  }

}
