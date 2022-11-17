package com.atguigu.spark.Value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object Spark02_Transformation_GroupBy {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境变量
    val conf: SparkConf = new SparkConf().setAppName("Spark02_Transformation_GroupBy").setMaster("local[*]")
    //    创建Sparkcontext，该对象是应用api接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建RDD，创建数组
    val RDD: RDD[Int] = sc.makeRDD(1 to 4, 2)
    val Groupby: Unit = RDD.groupBy(_ % 2).collect().foreach(println)
    sc.stop()
  }
}
