package com.atguigu.spark.day10

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.receiver.Receiver
import org.apache.spark.streaming.{Seconds, StreamingContext}

import java.io.{BufferedReader, InputStreamReader}
import java.net.{ConnectException, Socket}
import java.nio.charset.StandardCharsets


/**
 * @author: Benchmark boy
 * @date 2022/9/19 14:29
 * @Desc: 通过自定义数据源创建DStream
 */

object SparkStreaming03_CustomerReceiver {
  def main(args: Array[String]): Unit = {
    //    创建配置文件对象
    val conf: SparkConf = new SparkConf().setAppName("SparkStreaming03_CustomerReceiver").setMaster("local[*]")
    //    创建sparkStreaming 上下文环境对象
    val ssc: StreamingContext = new StreamingContext(conf, Seconds(3))
    //    通过自定义数据源创建DStream
    //    ssc.queueStream()
    //    ssc.socketTextStream()
    val myDS: ReceiverInputDStream[String] = ssc.receiverStream(new MyReceiver("hadoop201", 9999))
    //    扁平化
    val flatmapDS: DStream[String] = myDS.flatMap(_.split(" "))
    //    结构转换，计数
    val mapDS: DStream[(String, Int)] = flatmapDS.map((_, 1))
    //    聚合
    val reduceDS: DStream[(String, Int)] = mapDS.reduceByKey(_ + _)
    //    打印输出
    reduceDS.print()
    ssc.start()

    ssc.awaitTermination()
  }
}

//    Receiver[T]  泛型表示的是  读取的数据类型
class MyReceiver(host: String, port: Int) extends Receiver[String](StorageLevel.MEMORY_ONLY) {
  private var socket: Socket = _


  override def onStart(): Unit = {
    def receive() {
      try {
        //        创建连接
        socket = new Socket(host, port)
        //        根据连接对象获取输入流
        val reader: BufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream, StandardCharsets.UTF_8))
        //      定义个变量，用于接收读取到的一行数据
        var input: String = null
        while ((input = reader.readLine()) != null) {
          store(input)
        }

      } catch {
        case e: ConnectException =>
          restart(s"Error connecting to $host:$port", e)
          return
      } finally {
        onStop()
      }
    }


    new Thread("Socket Receiver") {
      setDaemon(true)

      override def run() {
        receive()
      }
    }.start()
  }

  override def onStop(): Unit = {
    // in case restart thread close it twice
    synchronized {
      if (socket != null) {
        socket.close()
        socket = null
      }
    }
  }


}

