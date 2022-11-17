package com.atguigu.spark.day05

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

import java.sql.{Connection, DriverManager, PreparedStatement}

/**
 * @Author: zpw
 * @Date: 2022-09-12:10:15
 * @Description：向MySQL数据库中写入数据
 * 注册驱动
 * 获取连接
 * 创建数据库操作对象PrepareStatement
 * 执行Sql
 * 处理结果集
 * 关闭连接
 *
 */
object Mysql_write {
  def main(args: Array[String]): Unit = {

    val conf: SparkConf = new SparkConf().setAppName("Mysql_write").setMaster("local[*]")
    val sc: SparkContext = new SparkContext(conf)

    var driver = "com.mysql.jdbc.Driver"
    var url = "jdbc:mysql://hadoop201:3306/Demon"
    var username = "root"
    var password = "000000"


    val rdd: RDD[(String, Int)] = sc.makeRDD(List(("bangmantg", 30), ("zhangepngwei", 28)))
    //    在循环体中创建连接对象，每次遍历出rdd中的一个元素，都需要创建一个连接对象，效率低不推荐使用
    /*    rdd.foreach {
          case (name, age) => {
            //        注册驱动
            Class.forName(driver)
            //        获取连接
            val conn: Connection = DriverManager.getConnection(url, username, password)
    //        声明数据库操作语句
            var sql="insert into Sparktest (name,age) values(?,?)"
            //        创建数据库操作对象preparestatement
            val ps: PreparedStatement = conn.prepareStatement(sql)
    //        给参数赋值
            ps.setString(1,name)
            ps.setInt(2,age)
    //        执行SQL
            ps.executeUpdate()


          }
        }*/
    rdd.foreachPartition {
      dates => {
        //        注册驱动
        Class.forName(driver)
        //        获取连接
        val conn: Connection = DriverManager.getConnection(url, username, password)
        //        声明数据库操作语句
        var sql = "insert into Sparktest (name,age) values(?,?)"
        //        创建数据库操作对象preparestatement
        val ps: PreparedStatement = conn.prepareStatement(sql)
        dates.foreach {
          case (name, age) => {
            //        给参数赋值
            ps.setString(1, name)
            ps.setInt(2, age)
            //        执行SQL
            ps.executeUpdate()
          }
        }
        ps.close()
        conn.close()
      }
    }

    sc.stop()

  }
}
