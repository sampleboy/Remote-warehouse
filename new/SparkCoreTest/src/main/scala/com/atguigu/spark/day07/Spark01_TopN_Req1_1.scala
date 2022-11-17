package com.atguigu.spark.day07

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ListBuffer

object Spark01_TopN_Req1_1 {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("Spark01_TopN_Req1_1").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)
    val datardd: RDD[String] = sc.textFile("E:\\尚硅谷 大数据  8月结课\\14，Spark\\2.资料\\spark-core数据\\user_visit_action.txt")
    //    将原始数据进行转换分解
    val actionrdd: RDD[UserVisitAction] = datardd.map {
      line => {
        val fields: Array[String] = line.split("_")
        //  封装成对象
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
    //    判断当前这条日志记录的是什么行为，并且封装成结果对象，（品类，点击数，下单数，支付数）===>例如，如果是鞋的点击行为，（鞋，1，0，0）
    val infordd: RDD[CategoryCountInfo] = actionrdd.flatMap {
      userAction => {
        //        判断是否为点击行为
        if (userAction.click_category_id != -1) {
          //          封装输出结果对象
          List(CategoryCountInfo(userAction.click_category_id + "", 1, 0, 0))
        } else if (userAction.order_category_ids != "null") { // 坑：读取的文件应该是null字符串。而不是null对象(关键字)
          //          判断是否为下单行为，如果是下单行为，需要对当前订单中涉及到的所有品类id进行切分
          val ids: Array[String] = userAction.order_category_ids.split(",")
          //          定义一个集合，用于存放多个品类id封装的输出结果对象
          val categoryCountInfoList = ListBuffer[CategoryCountInfo]()
          //          对所有的品类进行遍历
          for (id <- ids) {
            categoryCountInfoList.append(CategoryCountInfo(id, 0, 1, 0))
          }
          categoryCountInfoList
        } else if (userAction.pay_category_ids != "null") {
          //          判断是否为支付行为，如果是支付行为，需要对当前订单中涉及到的所有品类id进行切分
          val ids: Array[String] = userAction.pay_category_ids.split(",")
          //          定义一个集合，用于存放多个品类id封装的输出结果对象
          val categoryCountInfoList = ListBuffer[CategoryCountInfo]()
          //          对所有的品类进行遍历
          for (id <- ids) {
            categoryCountInfoList.append(CategoryCountInfo(id, 0, 0, 1))
          }
          categoryCountInfoList
        } else {
          Nil
        }
      }
    }
    //    将相同品类的分为一组
    val grouprdd: RDD[(String, Iterable[CategoryCountInfo])] = infordd.groupBy(info => info.categoryId)
    //    将分组后的数据进行聚合处理，返回一个元组
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
    //    转换结构为RDD（聚合后的CategoryCountInfo）
    val maprdd: RDD[CategoryCountInfo] = reducerdd.map(_._2)
    //    排序取前十元组会按照顺序排序
    val resrdd: Array[CategoryCountInfo] = maprdd.sortBy(info => (info.clickCount, info.orderCount, info.payCount), false).take(10)
    resrdd.foreach(println)
    sc.stop()
  }
}

//用户访问动作表
case class UserVisitAction(date: String, //用户点击行为的日期
                           user_id: Long, //用户的ID
                           session_id: String, //Session的ID
                           page_id: Long, //某个页面的ID
                           action_time: String, //动作的时间点
                           search_keyword: String, //用户搜索的关键词
                           click_category_id: Long, //某一个商品品类的ID
                           click_product_id: Long, //某一个商品的ID
                           order_category_ids: String, //一次订单中所有品类的ID集合
                           order_product_ids: String, //一次订单中所有商品的ID集合
                           pay_category_ids: String, //一次支付中所有品类的ID集合
                           pay_product_ids: String, //一次支付中所有商品的ID集合
                           city_id: Long) //城市 id

// 输出结果表
case class CategoryCountInfo(categoryId: String, //品类id
                             var clickCount: Long, //点击次数
                             var orderCount: Long, //订单次数
                             var payCount: Long) //支付次数

