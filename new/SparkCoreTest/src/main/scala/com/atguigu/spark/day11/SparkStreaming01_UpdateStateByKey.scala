package com.atguigu.spark.day11

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}


/**
 * @author: Benchmark boy
 * @date 2022/9/18 22:54
 * @Desc: 无状态转换-transform
 */

object SparkStreaming01_UpdateStateByKey {
  def main(args: Array[String]): Unit = {
    val conf: SparkConf = new SparkConf().setAppName("SparkStreaming01_Transform").setMaster("local[*]")
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(3))
    //    从指定端口读取数据
    val socketDS: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop201", 9999)
    //     设置检查点路径，状态保存在checkpoint中
    ssc.checkpoint("E:\\ideaproject\\new\\SparkCoreTest\\cp")
    //    转换为RDD操作
    //    val resRDD: DStream[(String, Int)] = socketDS.transform(
    //      rdd => {
    val flatMapRDD: DStream[String] = socketDS.flatMap(_.split(" "))
    val mapRDD = flatMapRDD.map((_, 1))

    //    reduceByKey 是无状态的，只会对当前采集周期的数据进行聚合操作
    //    val reduceRDD: RDD[(String, Int)] = mapRDD.reduceByKey(_ + _)
    //      reduceRDD.sortByKey()
    val resRDD: DStream[(String, Int)] = mapRDD.updateStateByKey(
      //      第一个参数：表示的相同的key对应的value组成的数据集合
      //      第二个参数：表示相同的key的缓冲区数据
      (seq: Seq[Int], state: Option[Int]) => {
        //      对当前的key对应的value进行求和
        //        seq.sum
        //      获取缓冲区的数据
        //        state.getOrElse(0)
        //        Option(1)
        Option(seq.sum + state.getOrElse(0))
      }
    )

    resRDD.print()
    ssc.start()
    ssc.awaitTermination()
  }
}
