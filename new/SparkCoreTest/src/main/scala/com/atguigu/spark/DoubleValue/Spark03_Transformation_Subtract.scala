package com.atguigu.spark.DoubleValue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark03_Transformation_Subtract {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境信息
    val conf: SparkConf = new SparkConf().setAppName("Spark03_Transformation_Union").setMaster("local[*]")
    //    创建SparkContext，该对象是应用app的接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建RDD，创建数组
    val RDD1: RDD[Int] = sc.makeRDD(1 to 4)
    val RDD2: RDD[Int] = sc.makeRDD(3 to 6)
    RDD1.subtract(RDD2).collect().foreach(println)
    sc.stop()


  }
}
