package com.atguigu.spark.Value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Transformation_sortBy {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境信息
    val conf: SparkConf = new SparkConf().setAppName("Spark02_Transformation_Sample").setMaster("local[*]")
    //    创建SparkContext，该对象是应用app的接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建RDD，创建数组
    val RDD: RDD[Int] = sc.makeRDD(Array(1, 8, 3, 4, 5, 6))
    //    默认是升序排序
    val sortRDD: RDD[Int] = RDD.sortBy(num => num)
    sortRDD.collect().foreach(println)
    //    配置为倒序排序
    val sortByRDD1: RDD[Int] = RDD.sortBy(num => num, false)
    sortByRDD1.collect().foreach(println)
    //    创建一个RDD
    val strRDD = sc.makeRDD(List("1", "22", "12", "2", "3"))
    //    按照字符的int值排序
    strRDD.sortBy(num => num.toInt).collect().foreach(println)

    val rdd3: RDD[(Int, Int)] = sc.makeRDD(List((2, 1), (1, 2), (1, 1), (2, 2)))
    rdd3.sortBy(t => t).collect().foreach(println)


    sc.stop()
  }
}
