package com.atguigu.spark.Keyvalue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark04_Transformation_ReduceByKey {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境信息
    val conf: SparkConf = new SparkConf().setAppName("Spark04_Transformation_ReduceByKey").setMaster("local[*]")
    //    创建SparkContext，该对象是应用app的接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建第一个RDD
    val rdd1: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 2), ("c", 3), ("b", 4)))
    //    计算相同key对应值的相加结果
    val reduce: RDD[(String, Int)] = rdd1.reduceByKey((x, y) => x + y)
    //    打印结果
    reduce.collect().foreach(println)
    sc.stop()
  }
}
