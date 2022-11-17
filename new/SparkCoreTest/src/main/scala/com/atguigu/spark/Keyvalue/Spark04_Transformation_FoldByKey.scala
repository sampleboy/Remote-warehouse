package com.atguigu.spark.Keyvalue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark04_Transformation_FoldByKey {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境信息
    val conf: SparkConf = new SparkConf().setAppName("Spark04_Transformation_ReduceByKey").setMaster("local[*]")
    //    创建SparkContext，该对象是应用app的接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建第一个RDD
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 5), ("a", 5), ("b", 2), ("c", 8), ("a", 3)), 2)

    rdd.foldByKey(0)(_ + _).collect().foreach(println)

    sc.stop()
  }
}
