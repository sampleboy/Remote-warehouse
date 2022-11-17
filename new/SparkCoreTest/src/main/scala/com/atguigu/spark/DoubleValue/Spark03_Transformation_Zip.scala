package com.atguigu.spark.DoubleValue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark03_Transformation_Zip {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境信息
    val conf: SparkConf = new SparkConf().setAppName("Spark03_Transformation_Union").setMaster("local[*]")
    //    创建SparkContext，该对象是应用app的接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建第一个RDD，创建数组
    val Rdd1: RDD[Int] = sc.makeRDD(Array(1, 2, 3), 3)
    val Rdd2: RDD[String] = sc.makeRDD(Array("a", "b", "c"), 3)
    Rdd1.zip(Rdd2).collect().foreach(print)
    //    Rdd2.zip(Rdd1).collect()foreach(println)




    sc.stop()
  }
}
