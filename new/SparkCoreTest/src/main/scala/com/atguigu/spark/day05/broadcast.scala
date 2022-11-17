package com.atguigu.spark.day05

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Author: Benchmark boy
 * @Date: 2022-09-12
 * @Description:
 */
object broadcast {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("broadcast").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3)))
    val rdd2: List[(String, Int)] = List(("a", 4), ("b", 5), ("c", 6))

    //声明广播变量
    val broadcastlist: Broadcast[List[(String, Int)]] = sc.broadcast(rdd2)
    val resrdd: RDD[(String, (Int, Int))] = rdd1.map {
      case (k1, v1) => {
        var v2 = 0
        for ((k3, v3) <- broadcastlist.value) {
          if (k1 == k3) {
            v3 == v2
          }
        }

        (k1, (v1, v2))
      }
    }
    resrdd.collect().foreach(println)
    sc.stop()
  }

}
