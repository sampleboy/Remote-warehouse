package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @Author: Benchmark boy
 * @Date: 2022-09-12
 * @Description:
 */
object accumulator01 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("accumulator01").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("a", 1), ("a", 3), ("a", 5), ("a", 1)))
    /*   val resrdd: RDD[(String, Int)] = rdd.reduceByKey(_ + _)
       resrdd.collect().foreach(println)
   */
    //    如果定义一个普通变量，那么在Driver定义，Executer会创建变量的副本，算子都是对副本进行操作，driver端的变量不会更新

    /*  var sum: Int = 0
      rdd.foreach {
        case (word, count) => {
          sum += count
        }
          println(sum)
      }*/

    val sum: LongAccumulator = sc.longAccumulator("myAcc")
    rdd.foreach {
      case (word, count) => {
        //        sum += count
        sum.add(count)
      }
    }
    println(sum)
    sc.stop()

  }

}
