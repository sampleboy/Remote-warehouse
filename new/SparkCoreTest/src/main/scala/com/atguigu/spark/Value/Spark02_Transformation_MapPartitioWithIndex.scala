package com.atguigu.spark.Value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object Spark02_Transformation_MapPartitioWithIndex {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf,设置环境参数
    val conf: SparkConf = new SparkConf().setAppName("Spark01_Transformation_MapPartitioWithIndex").setMaster("local[*]")
    //    创建SparkContext，该对象是api入口
    val sc: SparkContext = new SparkContext(conf)
    //    创建RDD,创建数组
    val RDD: RDD[Int] = sc.makeRDD(1 to 4, 2)
    //    创建一个RDD，使每个元素跟所在分区号形成一个元组，组成新的RDD
    val indexRDD: RDD[(Int, Int)] = RDD.mapPartitionsWithIndex((index, items) => {
      items.map((index, _))
    })
    indexRDD.collect().foreach(println)
    sc.stop()
  }
}
