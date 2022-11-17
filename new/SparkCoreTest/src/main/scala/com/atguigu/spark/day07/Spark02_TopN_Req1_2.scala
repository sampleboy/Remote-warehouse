package com.atguigu.spark.day07

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

object Spark02_TopN_Req1_2 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("Spark02_TopN_Req1_2").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    val datardd: RDD[String] = sc.textFile("E:\\尚硅谷 大数据  8月结课\\14，Spark\\2.资料\\spark-core数据\\user_visit_action.txt")
    val actionrdd: RDD[UserVisitAction] = datardd.map {
      datas => {
        val fields: Array[String] = datas.split("_")
        UserVisitAction(
          fields(0),
          fields(1).toLong,
          fields(2),
          fields(3).toLong,
          fields(4),
          fields(5),
          fields(6).toLong,
          fields(7).toLong,
          fields(8),
          fields(9),
          fields(10),
          fields(11),
          fields(12).toLong
        )
      }
    }
    val infordd: RDD[(String, CategoryCountInfo)] = actionrdd.flatMap {
      userAction => {
        if (userAction.click_category_id != -1) {
          List((userAction.click_category_id.toString, CategoryCountInfo(userAction.click_category_id.toString, 1, 0, 0)))
        } else if (userAction.order_category_ids != "null") {
          val ids: Array[String] = userAction.order_category_ids.split(",")
          val categoryCountInfoList: ListBuffer[(String, CategoryCountInfo)] = new ListBuffer[(String, CategoryCountInfo)]
          for (id <- ids) {
            categoryCountInfoList.append((id, CategoryCountInfo(id, 0, 1, 0)))
          }
          categoryCountInfoList
        } else if (userAction.pay_category_ids != "null") {
          val ids: Array[String] = userAction.pay_category_ids.split(",")
          val categoryCountInfoList: ListBuffer[(String, CategoryCountInfo)] = new ListBuffer[(String, CategoryCountInfo)]
          for (id <- ids) {
            categoryCountInfoList.append((id, CategoryCountInfo(id, 0, 0, 1)))
          }
          categoryCountInfoList
        } else {
          Nil
        }
      }
    }
    //    使用reducebykey进行预聚合
    val reducerdd: RDD[(String, CategoryCountInfo)] = infordd.reduceByKey {
      (info1, info2) => {
        info1.clickCount = info1.clickCount + info2.clickCount
        info1.orderCount = info1.orderCount + info2.orderCount
        info1.payCount = info1.payCount + info2.payCount
        info1
      }
    }
    //    转换结构为RDD
    val maprdd: RDD[CategoryCountInfo] = reducerdd.map(_._2)
    val resrdd: Array[CategoryCountInfo] = maprdd.sortBy(info => (info.clickCount, info.orderCount, info.payCount), false).take(10)
    resrdd.foreach(println)

  }

}

