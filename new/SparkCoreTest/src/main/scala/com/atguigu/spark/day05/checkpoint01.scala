package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object checkpoint01 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("checkpoint01").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    val rdd: RDD[String] = sc.textFile("E:\\ideaproject\\new\\SparkCoreTest\\input\\01.txt")
    sc.setCheckpointDir("./checkpoint1")
    val wordrdd: RDD[String] = rdd.flatMap {
      word => {
        word.split(" ")
      }
    }
    val wordtoone: RDD[(String, Long)] = wordrdd.map {
      word => {
        (word, System.currentTimeMillis())
      }
    }
    println(wordtoone.toDebugString)
    wordtoone.cache()
    wordtoone.checkpoint()

    wordtoone.collect().foreach(println)
    println("---------------------")
    println(wordtoone.toDebugString)


    wordtoone.collect().foreach(println)

    Thread.sleep(1000)
    sc.stop()


  }

}
