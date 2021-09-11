package com.mq.kafka.springbootdemo.controller;

import com.mq.kafka.springbootdemo.service.inter.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: railgun
 * 2021/9/11 11:04
 * PS:
 */
@Controller
@RequestMapping("kafka-spring-boot")
public class KafkaSpringBootController {

    @Autowired
    private DemoService demoService;

    /**
     * railgun
     * 2021/9/11 11:07
     * PS: send message
     */
    @ResponseBody
    @GetMapping("producer")
    public void producerExecute(String message) {
        demoService.producerExecute(message);
    }

}
