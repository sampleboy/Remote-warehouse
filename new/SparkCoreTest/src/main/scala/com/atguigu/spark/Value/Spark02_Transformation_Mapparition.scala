package com.atguigu.spark.Value


import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object Spark02_Transformation_Mapparition {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境变量
    val conf: SparkConf = new SparkConf().setAppName("Spark01_Transformation_Mapparition").setMaster("local[*]")
    //    创建SparkContext，该对象是应用api接口
    val sc: SparkContext = new SparkContext(conf)
    //   创建业务逻辑
    val RDD: RDD[Int] = sc.makeRDD(1 to 4, 2)
    //    调用mapPartitions方法，以分区为单位处理数据
    val RDD1: RDD[Int] = RDD.mapPartitions(x => x.map(_ * 2))
    RDD1.collect().foreach(println)
    sc.stop()
  }
}
