package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Operate_Json {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("Operate_Json").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    val jsonRDD: RDD[String] = sc.textFile("E:\\ideaproject\\new\\SparkCoreTest\\input\\01.json")
    import scala.util.parsing.json.JSON

    val resultRDD: RDD[Option[Any]] = jsonRDD.map(JSON.parseFull)

    //3.3 打印结果
    resultRDD.collect().foreach(println)

    //4.关闭连接
    sc.stop()

  }

}
