package com.atguigu.spark.day05

import org.apache.spark.rdd.JdbcRDD
import org.apache.spark.{SparkConf, SparkContext}

import java.sql.DriverManager

//sc: SparkContext,
//  getConnection: () => Connection,
//  sql: String,
//  lowerBound: Long,
//  upperBound: Long,
//  numPartitions: Int
//mapRow: (ResultSet) => T
object MysqlRDD {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("MysqlRDD").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)

    //    驱动四要素
    var driver = "com.mysql.jdbc.Driver"
    var url = "jdbc:mysql://hadoop201:3306/Demon"
    var userName = "root"
    var password = "000000"
    var sql: String = "select * from Sparktest where id>=? and id <=?"

    val resrdd: JdbcRDD[(Int, String, Int)] = new JdbcRDD(
      sc,
      () => {
        //        注册驱动
        Class.forName(driver)
        //        获取连接
        DriverManager.getConnection(url, userName, password)
      },
      sql,
      lowerBound = 1,
      upperBound = 20,
      numPartitions = 2,
      rs => (rs.getInt(1), rs.getString(2), rs.getInt(3))
    )
    resrdd.collect().foreach(println)
    sc.stop()
  }
}
