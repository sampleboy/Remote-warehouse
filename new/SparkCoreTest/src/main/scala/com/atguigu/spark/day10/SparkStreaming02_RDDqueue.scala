package com.atguigu.spark.day10

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, InputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable


/**
 * @author: Benchmark boy
 * @date 2022/9/19 14:29
 * @Desc: 通过RDD队列的方式创建DStream
 *        需求：循环创建几个RDD，将RDD放入队列，通过SparkStream创建Dstream,计算wordcount
 */

object SparkStreaming02_RDDqueue {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("SparkStreaming02_RDDqueue").setMaster("local[*]")
    //    创建Spark streaming上下文环境对象
    val scc: StreamingContext = new StreamingContext(conf, Seconds(3))
    //    创建队列，里面放的是RDD
    val rddQueue: mutable.Queue[RDD[Int]] = new mutable.Queue[RDD[Int]]()
    //    从队列中采集数据，获取DS
    val queueDS: InputDStream[Int] = scc.queueStream(rddQueue)
    //    处理采集到的数据
    val resDS: DStream[(Int, Int)] = queueDS.map((_, 1)).reduceByKey(_ + _)
    //    打印结果
    resDS.print()
    //    启动采集器
    scc.start()

    //    循环创建RDD，并将创建的RDD放到队列里
    for (i <- 1 to 5) {
      rddQueue.enqueue(scc.sparkContext.makeRDD(6 to 10))
      Thread.sleep(3000)
    }
    scc.awaitTermination()

  }
}