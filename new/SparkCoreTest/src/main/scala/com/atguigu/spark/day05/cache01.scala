package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object cache01 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("cache01").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    //    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)
    val rdd: RDD[String] = sc.textFile("E:\\ideaproject\\new\\SparkCoreTest\\input")
    val oneToword: RDD[String] = rdd.flatMap(_.split(" "))
    //    val result: RDD[(String, Int)] = oneToword.map((_, 1))
    val resultrdd: RDD[(String, Int)] = oneToword.map {
      word => {
        println("---------------------------")
        (word, 1)
      }
    }

    println(resultrdd.toDebugString)
    resultrdd.cache()

    resultrdd.collect()

    println("-----------------")
    println(resultrdd.toDebugString)

    resultrdd.collect()


    sc.stop()
  }

}
