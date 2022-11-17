package com.atguigu.spark.Value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Transformation_Filter {
  def main(args: Array[String]): Unit = {
    //    创建Sparkconf，设置应用环境
    val conf: SparkConf = new SparkConf().setAppName("Spark02_Transformation_Filter").setMaster("local[*]")
    //    创建Sparkcontext，该对象是应用app的接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建RDD，创建数组
    val RDD: RDD[Int] = sc.makeRDD(Array(1, 2, 3, 4), 2)
    //    过滤出符合条件的数据
    val filterRDD: RDD[Int] = RDD.filter(_ % 2 == 0)
    filterRDD.collect().foreach(println)
    sc.stop()

  }
}
