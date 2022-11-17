package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object checkpoint02 {
  def main(args: Array[String]): Unit = {
    System.setProperty("HADOOP_USER_NAME", "atguigu")
    val conf: SparkConf = new SparkConf().setAppName("checkpoint02").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    sc.setCheckpointDir("hdfs://hadoop201:8020/checkpoint")
    val linerdd: RDD[String] = sc.textFile("E:\\ideaproject\\new\\SparkCoreTest\\input\\01.txt")
    val wordrdd: RDD[String] = linerdd.flatMap {
      word => word.split(" ")
    }
    val wordtoOne: RDD[(String, Long)] = wordrdd.map {
      word => {
        (word, System.currentTimeMillis())
      }
    }
    //    增加缓存避免重新跑一个job做checkpoint
    wordtoOne.cache()
    //    数据检查点，针对wordtoOne做个检查点计算
    wordtoOne.checkpoint()

    wordtoOne.collect().foreach(println)
    sc.stop()

  }

}
