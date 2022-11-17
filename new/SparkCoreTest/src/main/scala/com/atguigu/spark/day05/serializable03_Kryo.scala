package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object serializable03_Kryo {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf,设置环境
    val conf: SparkConf = new SparkConf().setAppName("serializable03_Kryo").setMaster("local[*]")

      .set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      // 注册需要使用 kryo 序列化的自定义类
      .registerKryoClasses(Array(classOf[Searcher]))
    //      创建SparkContext，该对象是api入口
    val sc: SparkContext = new SparkContext(conf)
    //    创建RDD
    val rdd: RDD[String] = sc.makeRDD(Array("hello world", "hello atguigu", "atguigu", "hahah"), 2)
    val searcher: Searcher = new Searcher("hello")

    val result: RDD[String] = searcher.getMatchedRDD1(rdd)
    result.collect().foreach(println)


  }

}


case class Searcher(val query: String) {

  def getMatchedRDD1(rdd: RDD[String]) = {
    rdd.filter(isMatch)
  }

  def isMatch(s: String) = {
    s.contains(query)
  }

  def getMatchedRDD2(rdd: RDD[String]) = {
    val q = query
    rdd.filter(_.contains(q))
  }
}



