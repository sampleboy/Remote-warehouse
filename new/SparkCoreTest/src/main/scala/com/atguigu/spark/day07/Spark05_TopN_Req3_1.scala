package com.atguigu.spark.day07

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * @author: Benchmark boy
 * @date 2022/9/14 22:49
 * @Desc: 页面单跳转化率统计
 */

object Spark05_TopN_Req3_1 {
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
    //-------------------------------------------------------------------------------------
    //      对当前日志中记录的访问页面进行计数
    val pageIdrdd: RDD[(Long, Long)] = actionrdd.map {
      action => {
        (action.page_id, 1L)
      }
    }
    //    通过页面的计数，计算没一个页面出现的总次数，作为求单条转换率的分母
    val fmIdsmap: Map[Long, Long] = pageIdrdd.reduceByKey(_ + _).collect().toMap
    //    计算分子
    //    将原始数据根据sessionID进行分组
    val sessionrdd: RDD[(String, Iterable[UserVisitAction])] = actionrdd.groupBy(_.session_id)
    //    得到排序后的同一个session的用户访问行为
    val pageFlowrdd: RDD[(String, List[(String, Int)])] = sessionrdd.mapValues {
      datas => {
        //        得到排序后的数据按照时间进行升序排序
        val useraction: List[UserVisitAction] = datas.toList.sortWith {
          (left, right) => {
            left.action_time < right.action_time
          }
        }
        //        对排序后的用户访问行为进行结构转换，只保留页面就可以了
        val pageIdsList: List[Long] = useraction.map(_.page_id)
        //        对当前会话用户访问页面，进行拉链，得到页面的流转情况
        val pageFlows: List[(Long, Long)] = pageIdsList.zip(pageIdsList.tail)
        //        对拉链后的数据，进行结构的转换
        pageFlows.map {
          case (page1, page2) => {
            (page1 + "_" + page2, 1)
          }
        }
      }
    }
    //    将没一个会话的页面跳转统计完毕后，没有必要保留会话信息了，所以对上述rdd的结构进行转换
    //    只保留页面跳转以及计数
    val pageFlowMapRDD: RDD[(String, Int)] = pageFlowrdd.map(_._2).flatMap(list => list)

    val page1AndPage2ToSumRDD: RDD[(String, Int)] = pageFlowMapRDD.reduceByKey(_ + _)

    //计算页面单跳转换率
    page1AndPage2ToSumRDD.foreach {
      case (pageFlow, fz) => {
        val pageIds: Array[String] = pageFlow.split("-")
        //为了避免分母不存在，这里默认值给1
        val fmSum: Long = fmIdsmap.getOrElse(pageIds(0).toLong, 1L)
        println(pageFlow + "=" + fz.toDouble / fmSum)
      }
    }


    sc.stop()


  }
}
