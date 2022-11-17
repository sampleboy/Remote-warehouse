package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Author: Benchmark boy
 * @Date: 2022-09-12
 * @Description:
 */
object accumulator_define {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("accumulator_define").setMaster("local[*]")
    val sc: SparkContext = new SparkContext()
    val rdd: RDD[String] = sc.makeRDD(List("hello", "hello", "hello", "spark", "spark"))

  }

}

