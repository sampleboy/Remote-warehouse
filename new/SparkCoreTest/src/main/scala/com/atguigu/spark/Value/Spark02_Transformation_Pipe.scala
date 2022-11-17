package com.atguigu.spark.Value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Transformation_Pipe {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境
    val conf: SparkConf = new SparkConf().setAppName("Spark02_Transformation_Distinct").setMaster("local[*]")
    //  创建SparkContext,该对象是应用app的api
    val sc: SparkContext = new SparkContext(conf)
    //    创建RDD，创建数组
    val distinctRDD: RDD[Int] = sc.makeRDD(Array(1, 2, 3, 4), 2)


  }
}
