package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Lineage01 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("Lineage01").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    val filerdd: RDD[String] = sc.textFile("E:\\ideaproject\\new\\SparkCoreTest\\input\\01.txt")
    println(filerdd.dependencies)
    println("-----------------------------")
    val wordrdd: RDD[String] = filerdd.flatMap((_.split(" ")))
    println(wordrdd.dependencies)
    println("-------------------------------")
    val maprdd: RDD[(String, Int)] = wordrdd.map((_, 1))
    println(maprdd.dependencies)
    println("-------------------------------")
    val resultrdd: RDD[(String, Int)] = maprdd.reduceByKey(_ + _)
    println(resultrdd.dependencies)


    resultrdd.collect()
    sc.stop()


  }

}
