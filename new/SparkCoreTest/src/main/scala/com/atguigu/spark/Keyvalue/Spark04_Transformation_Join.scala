package com.atguigu.spark.Keyvalue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark04_Transformation_Join {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境信息
    val conf: SparkConf = new SparkConf().setAppName("Spark04_Transformation_ReduceByKey").setMaster("local[*]")
    //    创建SparkContext，该对象是应用app的接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建第一个RDD
    val rdd: RDD[(Int, String)] = sc.makeRDD(Array((3, "aa"), (6, "cc"), (2, "bb"), (1, "dd")), 2)
    //    创建第二个RDD
    val rdd2: RDD[(Int, Int)] = sc.makeRDD(Array((1, 2), (3, 8), (2, 6), (4, 1)), 2)
    rdd.join(rdd2).collect().foreach(println)

    sc.stop()
  }
}
