package com.atguigu.spark

import redis.clients.jedis.{Jedis, JedisPool, JedisPoolConfig}

/**
 * @author: Benchmark boy
 * @date 2022/9/28 17:17
 * @Desc:
 */
object RedisUtil {
  var jedisPool: JedisPool = null

  def getJedisClient: Jedis = {
    if (jedisPool == null) {
      //println("开辟一个连接池")
      val host = "linux4"
      val port = 6379

      val jedisPoolConfig = new JedisPoolConfig()
      jedisPoolConfig.setMaxTotal(100) //最大连接数
      jedisPoolConfig.setMaxIdle(20) //最大空闲
      jedisPoolConfig.setMinIdle(20) //最小空闲
      jedisPoolConfig.setBlockWhenExhausted(true) //忙碌时是否等待
      jedisPoolConfig.setMaxWaitMillis(500) //忙碌时等待时长 毫秒
      jedisPoolConfig.setTestOnBorrow(true) //每次获得连接的进行测试

      jedisPool = new JedisPool(jedisPoolConfig, host, port.toInt)
    }
    //println(s"jedisPool.getNumActive = ${jedisPool.getNumActive}")
    //println("获得一个连接")
    jedisPool.getResource
  }

}
