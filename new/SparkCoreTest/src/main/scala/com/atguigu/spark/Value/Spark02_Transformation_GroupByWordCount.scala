package com.atguigu.spark.Value

import org.apache.spark.{SparkConf, SparkContext}


object Spark02_Transformation_GroupByWordCount {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境参数
    val conf = new SparkConf().setAppName("Spark02_Transformation_GroupByWordCount").setMaster("local[*]")
    //    创建SparkContext，该对象是提交app的接口
    val sc = new SparkContext(conf)
    //    创建RDD，创建数组
    val strList = List("Hello Scala", "Hello Spark", "Hello Word")
    val RDD = sc.makeRDD(strList)
    //    将字符串拆成一个个的单词
    val wordRDD = RDD.flatMap(_.split(" "))
    //    将单词结果，转成(key,value)
    val wordToOneRdd = wordRDD.map((_, 1))
    //    将转换结构后的数据分组
    val groupRdd = wordToOneRdd.groupBy(t => t._1)
    //    将分组后的数据进行结构的转换
    val wordToSum = groupRdd.map {
      case (word, list) => {
        (word, list.size)
      }
    }
    wordToSum.collect().foreach(println)
    sc.stop()
  }
}
