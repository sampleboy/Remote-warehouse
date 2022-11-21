package com.atguigu.gmall0105.logger.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
//        @RestController= @Controller + @ResponseBody
@Slf4j
public class Loggercontroller {
    @Autowired
    KafkaTemplate KafkaTemplate;

    //    ResponseBody  决定方法的返回值是网页还是文本
    @ResponseBody
    @RequestMapping("/applog")
    public String applog(@RequestBody String logString) {
        System.out.println(logString);
        log.info(logString);
//        分流
        JSONObject jsonObject = JSON.parseObject(logString);
        if (jsonObject.getString("start") != null && jsonObject.getString("start").length() > 0) {
//            启动日志
            KafkaTemplate.send("GMALL_START_UP_0105", logString);
        } else {
//          事件日志
            KafkaTemplate.send("GMALL_EVENT_0105", logString);
        }
        return logString;
    }
}
