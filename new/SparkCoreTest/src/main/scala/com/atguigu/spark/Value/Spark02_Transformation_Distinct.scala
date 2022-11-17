package com.atguigu.spark.Value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Transformation_Distinct {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境
    val conf: SparkConf = new SparkConf().setAppName("Spark02_Transformation_Distinct").setMaster("local[*]")
    //  创建SparkContext,该对象是应用app的api
    val sc: SparkContext = new SparkContext(conf)
    //    创建RDD，创建数组
    val distinctRDD: RDD[Int] = sc.makeRDD(List(1, 2, 2, 2, 3, 3, 3, 4, 5, 6, 6, 6))
    //    打印去重后生成新的RDD
    //    distinctRDD.distinct().collect().foreach(println)
    distinctRDD.distinct(2).collect().foreach(println)
    sc.stop()

  }
}
