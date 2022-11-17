package com.atguigu.spark.Value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Transformation_Repartition {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境信息
    val conf: SparkConf = new SparkConf().setAppName("Spark02_Transformation_Sample").setMaster("local[*]")
    //    创建SparkContext，该对象是应用app的接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建RDD，创建数组
    val RDD: RDD[Int] = sc.makeRDD(Array(1, 2, 3, 4, 5, 6), 3)
    //    缩减分区
    //    val coalesce: RDD[Int] = RDD.coalesce(2)
    //    重新分区
    val rePartitionRDD: RDD[Int] = RDD.repartition(2)
    //    打印查看对应分区数据
    val indexRDD = rePartitionRDD.mapPartitionsWithIndex(
      (index, datas) => {
        datas.foreach(data => {
          println(index + "=>" + data)
        })
        datas
      }
    )
    indexRDD.collect()
    sc.stop()
  }
}
