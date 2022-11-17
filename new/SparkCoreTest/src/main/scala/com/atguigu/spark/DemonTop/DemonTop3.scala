package com.atguigu.spark.DemonTop

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 */
object DemonTop3 {
  def main(args: Array[String]): Unit = {
    //    创建Sparkconf，设置环境
    val conf: SparkConf = new SparkConf().setAppName("DemonTop3").setMaster("local[*]")
    //    创建SparkContext，该对象是应用APP的接口new Sap
    val sc: SparkContext = new SparkContext(conf)
    //    读取外部文件
    val logrdd: RDD[String] = sc.textFile("E:\\尚硅谷 大数据  8月结课\\14，Spark\\2.资料\\agent.log")
    //    对读取的数据，进行结构转换（省份id，广告id）、
    val maprdd: RDD[(String, Int)] = logrdd.map {
      line => {
        //        用空格对读取的一行数据进行切分
        val fields: Array[String] = line.split(" ")
        //        封装为元组结构返回
        (fields(1) + "-" + fields(4), 1)
      }
    }
    //    对当前省份的没一个广告点击次数进行聚合
    val reducerdd: RDD[(String, Int)] = maprdd.reduceByKey(_ + _)
    //    再次对省份进行调整，将省份作为key，
    val map1rdd: RDD[(String, (String, Int))] = reducerdd.map {
      case (proAndAd, clickCount) => {
        val proAndAdArr: Array[String] = proAndAd.split("-")
        (proAndAdArr(0), (proAndAdArr(1), clickCount))
      }
    }
    //    按照省份进行分组，（省份，（string,int））
    val groupRDD: RDD[(String, Iterable[(String, Int)])] = map1rdd.groupByKey()
    //    对每个省份中的广澳点击次数进行降序排序并取前三名
    val mapValueRDD: RDD[(String, List[(String, Int)])] = groupRDD.mapValues {
      datas => {
        datas.toList.sortWith(
          (left, right) => {
            left._2 > right._2
          }
        ).take(3)
      }
    }
    mapValueRDD.collect().foreach(println)
    sc.stop()
  }


}
