package com.atguigu.spark.Keyvalue

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Spark04_Transformation_GroupBykey {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境信息
    val conf: SparkConf = new SparkConf().setAppName("Spark04_Transformation_ReduceByKey").setMaster("local[*]")
    //    创建SparkContext，该对象是应用app的接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建第一个RDD
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("b", 5), ("a", 5), ("b", 2)))
    //    将相同key对应值聚合到一个Seq中
    val group: RDD[(String, Iterable[Int])] = rdd.groupByKey()
    group.collect().foreach(println)


    //    计算相同key对应值的相加结果
    val keySum: Array[(String, Int)] = group.map(t => (t._1, t._2.sum)).collect()
    println(keySum)
    sc.stop()
  }
}
