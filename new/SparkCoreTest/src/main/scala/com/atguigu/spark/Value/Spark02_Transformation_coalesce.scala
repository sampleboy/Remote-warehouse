package com.atguigu.spark.Value

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark02_Transformation_coalesce {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境信息
    val conf: SparkConf = new SparkConf().setAppName("Spark02_Transformation_Sample").setMaster("local[*]")
    //    创建SparkContext，该对象是应用app的接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建RDD，创建数组
    val RDD: RDD[Int] = sc.makeRDD(Array(1, 2, 3, 4, 5, 6), 3)
    //    RDD.collect().foreach(println)
    //    缩减分区
    val coalesceRdd: RDD[Int] = RDD.coalesce(2)
    //    coalesceRdd.collect().foreach(println)
    //    打印查看对应分区数据
    val indexRdd: RDD[Int] = coalesceRdd.mapPartitionsWithIndex(
      (index, datas) => {
        //        打印每个分区数据，并带分区号
        datas.foreach(data => {
          println(index + "=>" + data)
        })
        //        返回分区的数据
        datas
      }
    )
    indexRdd.collect()
    sc.stop()
  }
}
