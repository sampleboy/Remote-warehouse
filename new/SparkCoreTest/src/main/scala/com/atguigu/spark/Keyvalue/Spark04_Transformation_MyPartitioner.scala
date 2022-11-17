package com.atguigu.spark.Keyvalue

import org.apache.spark.rdd.RDD
import org.apache.spark.{Partitioner, SparkConf, SparkContext}

object Spark04_Transformation_MyPartitioner {
  def main(args: Array[String]): Unit = {
    //    创建SparkConf，设置环境信息
    val conf: SparkConf = new SparkConf().setAppName("Spark03_Transformation_Union").setMaster("local[*]")
    //    创建SparkContext，该对象是应用app的接口
    val sc: SparkContext = new SparkContext(conf)
    //    创建第一个RDD
    val rdd: RDD[(Int, String)] = sc.makeRDD(Array((1, "aaa"), (2, "bbb"), (3, "ccc")), 3)
    //    自定义分区
    val rdd2: RDD[(Int, String)] = rdd.partitionBy(new MyPartitioner(2))
    //    打印查看对应分区数据
    val indexRdd: RDD[(Int, String)] = rdd2.mapPartitionsWithIndex(
      (index, datas) => {
        //        打印每个分区数据，并带分区号
        datas.foreach(data => {
          println(index + "=>" + data)
        })
        datas
      }
    )
    indexRdd.collect()
    sc.stop()
  }
}

//自定义分区
class MyPartitioner(partitons: Int) extends Partitioner {
  override def numPartitions: Int = partitons

  override def getPartition(key: Any): Int = {
    //      asInstanceof 转换
    val keyInt: Int = key.asInstanceOf[Int]
    if (key.isInstanceOf[Int]) {

      if (keyInt % 2 == 0)
        0
      else
        1
    } else {
      0
    }


    /*
    override def getPartition(key: Any): Int = {
    //      asInstanceof 转换为string类型
          val keyInt: string = key.asInstanceOf[string]
          if(key.startswith("136")){

          0
          }else if (key.startswith("138")){
          1
          }else{
          0
          }
     */
  }
}