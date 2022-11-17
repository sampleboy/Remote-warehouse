package com.atguigu.spark.Value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object Spark02_Transformation_Glom {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境变量
    val conf: SparkConf = new SparkConf().setAppName("Spark02_Transformation_Glom").setMaster("local[*]")
    //    创建SparkContext，该对象是应用api接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建RDD，创建业务逻辑
    val RDD: RDD[Int] = sc.makeRDD(1 to 4, 2)
    //    求出每个分区的最大值
    val maxRDD: RDD[Int] = RDD.glom().map(_.max)
    maxRDD.collect().foreach(println)
    sc.stop()
  }
}
