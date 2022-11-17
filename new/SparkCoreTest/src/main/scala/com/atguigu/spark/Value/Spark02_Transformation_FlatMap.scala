package com.atguigu.spark.Value

import org.apache.spark.{SparkConf, SparkContext}


object Spark02_Transformation_FlatMap {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境参数
    val conf: SparkConf = new SparkConf().setAppName("Spark02_Transformation_FlatMap").setMaster("local[*]")

    //    val conf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")
    //    创建SparkContext，该对象是api接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建RDD，创建数据集合
    val listRDD = sc.makeRDD(List(List(1, 2), List(3, 4), List(5, 6), List(7)), 2)
    //    val listRDD = sc.makeRDD(List(List(1, 2), List(3, 4), List(5, 6), List(7)), 2)
    //    把所有子集合中数据取出放入到大集合中
    listRDD.flatMap(list => list).collect.foreach(println)
    sc.stop()

  }
}
