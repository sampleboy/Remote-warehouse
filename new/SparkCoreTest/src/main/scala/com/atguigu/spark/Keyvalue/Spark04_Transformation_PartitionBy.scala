package com.atguigu.spark.Keyvalue

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object Spark04_Transformation_PartitionBy {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境信息
    val conf: SparkConf = new SparkConf().setAppName("Spark03_Transformation_Union").setMaster("local[*]")
    //    创建SparkContext，该对象是应用app的接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建第一个RDD
    val RDD: RDD[(Int, String)] = sc.makeRDD(Array((1, "aaa"), (2, "bbb"), (3, "ccc")), 3)
    //    对RDD进行重新分组
    val RDD2: RDD[(Int, String)] = RDD.partitionBy(new HashPartitioner(2))
    //    查看新的RDD的分区数
    println(RDD2.partitions.size)
    sc.stop()

  }
}
