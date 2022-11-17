package com.atguigu.spark.day07

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

/**
 * @author: Benchmark boy
 * @date 2022/9/13
 * @Desc: 获取TopN热门品类的id  topNList来源需求一
 */

//通过需求1，获取TopN热门品类的id
//将原始数据进行过滤（1.保留热门品类 2.只保留点击操作）
//对session的点击数进行转换 (category-session,1)
//对session的点击数进行统计 (category-session,sum)
//将统计聚合的结果进行转换  (category,(session,sum))
//将转换后的结构按照品类进行分组 (category,Iterator[(session,sum)])
//对分组后的数据降序 取前10

object Spark03_TopN_Req2_1 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("Spark03_TopN_Req2_1").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    val datardd: RDD[String] = sc.textFile("E:\\尚硅谷 大数据  8月结课\\14，Spark\\2.资料\\spark-core数据\\user_visit_action.txt")
    val actionrdd: RDD[UserVisitAction] = datardd.map {
      line => {
        val fields: Array[String] = line.split("_")
        UserVisitAction.apply(
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
    val infordd: RDD[CategoryCountInfo] = actionrdd.flatMap {
      userAction => {
        if (userAction.click_category_id != -1) {
          List(CategoryCountInfo(userAction.click_category_id.toString, 1, 0, 0))
        } else if (userAction.order_category_ids != "null") {
          val ids: Array[String] = userAction.order_category_ids.split(",")
          val categoryCountInfoList: ListBuffer[CategoryCountInfo] = new ListBuffer[CategoryCountInfo]()
          for (id <- ids) {
            categoryCountInfoList.append(CategoryCountInfo(id, 0, 1, 0))
          }
          categoryCountInfoList
        } else if (userAction.pay_category_ids != "null") {
          val ids: Array[String] = userAction.order_category_ids.split(",")
          val categoryCountInfoList: ListBuffer[CategoryCountInfo] = new ListBuffer[CategoryCountInfo]()
          for (id <- ids) {
            categoryCountInfoList.append(CategoryCountInfo(id, 0, 0, 1))
          }
          categoryCountInfoList
        } else {
          Nil
        }

      }
    }

    val grouprdd: RDD[(String, Iterable[CategoryCountInfo])] = infordd.groupBy(info => info.categoryId)

    val reducerdd: RDD[(String, CategoryCountInfo)] = grouprdd.mapValues {
      datas => {
        datas.reduce {
          (info1, info2) => {
            info1.clickCount = info1.clickCount + info2.clickCount
            info1.orderCount = info1.orderCount + info2.orderCount
            info1.payCount = info1.payCount + info2.payCount
            info1
          }
        }
      }
    }
    val maprdd: RDD[CategoryCountInfo] = reducerdd.map(_._2)
    val res: Array[CategoryCountInfo] = maprdd.sortBy(info => (info.clickCount, info.orderCount, info.payCount), false).take(10)
    //    resrdd.foreach(println)
    //    ---------------------------------------------------------------------------------------------------------
    //    热门品类id
    val ids: Array[String] = res.map(_.categoryId)
    val broadcast: Broadcast[Array[String]] = sc.broadcast(ids)
    //    将原始数据进行过滤（1.保留热门品类 2.只保留点击操作）
    val filerrdd: RDD[UserVisitAction] = actionrdd.filter {
      action => {
        if (action.click_category_id != -1) {
          //          还需要判断是否是热门品类的id
          broadcast.value.contains(action.click_category_id.toString)
        } else {
          false
        }
      }
    }
    //3.对session的点击数进行转换 (category-session,1)
    val maprdd1: RDD[(String, Int)] = filerrdd.map {
      action => (action.click_category_id + "_" + action.session_id, 1)
    }
    //对session的点击数进行统计 (category-session,sum)
    val reducerdd1: RDD[(String, Int)] = maprdd1.reduceByKey(_ + _)
    //将统计聚合的结果进行转换  (category,(session,sum))
    val maprdd2: RDD[(String, (String, Int))] = maprdd1.map {
      case (k, sum) => { //无switch，需要case循环
        val categoryAndSession: Array[String] = k.split("_")
        (categoryAndSession(0), (categoryAndSession(1), sum))
      }
    }
    val grouprdd2: RDD[(String, Iterable[(String, Int)])] = maprdd2.groupByKey()
    //    对分组后的数据进行排序
    val resrdd2: RDD[(String, List[(String, Int)])] = grouprdd2.mapValues {
      datas => {
        datas.toList.sortWith {
          case (left, right) => {
            left._2 > right._2
          }
        }
      }.take(10)
    }
    resrdd2.foreach(println)


    sc.stop()
  }
}
