package com.atguigu.spark.day01

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object Spark05_Partition_file {
  def main(args: Array[String]): Unit = {
    //    创建配置文件对象
    val conf: SparkConf = new SparkConf().setAppName("spark01_createrdd_file").setMaster("local[*]")
    //    创建Sparkcontext对象
    val sc: SparkContext = new SparkContext(conf)
    //    读取文件
    //    val rdd: RDD[String] = sc.textFile("hdfs://hadoop201:8020/input")
    val rdd: RDD[String] = sc.textFile("E:\\ideaproject\\new\\SparkCoreTest\\input\\01.txt")
    rdd.foreach(println)

    //    关闭资源
    sc.stop()


  }

}
