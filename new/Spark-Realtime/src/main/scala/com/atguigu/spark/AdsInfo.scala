package com.atguigu.spark

import java.sql.Timestamp

/**
 * @author: Benchmark boy
 * @date: 2022/9/28 17:17
 * @Desc:
 */
case class AdsInfo(ts: Long,
                   timestamp: Timestamp,
                   dayString: String,
                   hmString: String,
                   area: String,
                   city: String,
                   userId: String,
                   adsId: String)
