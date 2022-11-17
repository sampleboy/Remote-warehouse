package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Stage01 {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf 设置环境参数
    val conf: SparkConf = new SparkConf().setAppName("Stage01").setMaster("local[*]")
    //    创建SparkContext，该对象是应用API的接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建RDD
    val datardd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4, 1, 2), 2)
    //    聚合
    val resultrdd: RDD[(Int, Int)] = datardd.map((_, 1)).reduceByKey(_ + _)
    resultrdd.collect().foreach(println)
    resultrdd.saveAsTextFile("E:\\ideaproject\\new\\SparkCoreTest\\output")

    Thread.sleep(1000)
    sc.stop()
  }

}
