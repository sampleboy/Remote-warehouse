package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object cache02 {
  def main(args: Array[String]): Unit = {
    //1.创建SparkConf并设置App名称
    val conf: SparkConf = new SparkConf().setAppName("SparkCoreTest").setMaster("local[*]")

    //2.创建SparkContext，该对象是提交Spark App的入口
    val sc: SparkContext = new SparkContext(conf)

    //3. 创建一个RDD，读取指定位置文件:hello atguigu atguigu
    val lineRdd: RDD[String] = sc.textFile("E:\\ideaproject\\new\\SparkCoreTest\\input\\01.txt")

    //3.1.业务逻辑
    val wordRdd: RDD[String] = lineRdd.flatMap(line => line.split(" "))

    val wordToOneRdd: RDD[(String, Int)] = wordRdd.map {
      word => {
        println("************")
        (word, 1)
      }
    }

    val wordByKeyRdd: RDD[(String, Int)] = wordToOneRdd.reduceByKey(_ + _)
    println(wordByKeyRdd.toDebugString)
    wordByKeyRdd.cache()
    wordByKeyRdd.collect()
    println("---------------------")
    println(wordByKeyRdd.toDebugString)
    wordByKeyRdd.collect()
    sc.stop()


  }

}
