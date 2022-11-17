package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object serializable02_function {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("serializable02_function").setMaster("local[*]")

    val sc: SparkContext = new SparkContext(conf)

    val rdd: RDD[String] = sc.makeRDD(Array("hello world", "hello spark", "hive", "atguigu"))
    val search = new Search("hello")

    search.getMatch1(rdd).collect().foreach(println)

    search.getMatche2(rdd).collect().foreach(println)

  }

}

class Search(query: String) extends Serializable {

  // 函数序列化案例
  def getMatch1(rdd: RDD[String]): RDD[String] = {
    //rdd.filter(this.isMatch)
    rdd.filter(isMatch)
  }

  def isMatch(s: String): Boolean = {
    s.contains(query)
  }

  // 属性序列化案例
  def getMatche2(rdd: RDD[String]): RDD[String] = {
    //rdd.filter(x => x.contains(this.query))
    rdd.filter(x => x.contains(query))
    //val q = query
    //rdd.filter(x => x.contains(q))
  }


}
