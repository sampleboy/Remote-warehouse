package com.atguigu.spark.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark03_Partition_default {
  def main(args: Array[String]): Unit = {


    //  创建配置对象
    val conf: SparkConf = new SparkConf().setAppName("Spark03_Partition_default").setMaster("local[*]")

    //  创建spark context对象，该对象是提交sparkapp的入口
    val sc: SparkContext = new SparkContext(conf)
    //  通过集合创建RDD
    val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), numSlices = 5)
    //读取外部文件创建RDD
    //  val rdd: RDD[String] = sc.textFile("E:\\ideaproject\\new\\SparkCoreTest\\input")
    //分区效果
    println(rdd.partitions.size)
    rdd.saveAsTextFile("E:\\ideaproject\\new\\SparkCoreTest\\output")
    //  关闭资源
    sc.stop()


  }
}