package com.mq.kafka.springbootdemo.controller;

import com.mq.kafka.springboot.autoconfigure.service.inter.HelloService;
import com.mq.kafka.springbootdemo.service.inter.DemoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @Autowired
    @Qualifier(value = "railgunHelloService")
    private HelloService helloService;

    /**
     * railgun
     * 2021/9/11 11:07
     * PS: send message
     */
    @ResponseBody
    @GetMapping("producer")
    public void producerExecute(String message) {
        int num = 100;
        for (int i = 0; i < num; i++) {
            demoService.producerExecute(message + " 第 " + i + " 条消息。");
        }
    }

    /**
     * railgun
     * 2021/9/14 0:39
     * PS: 测试事务发送消息
     */
    @ResponseBody
    @GetMapping("send/{input}")
    @Transactional(rollbackFor = Exception.class)
    public void sendInput(@PathVariable("input") String input) throws Exception {
        demoService.producerExecute("这是第一条消息");
        if (StringUtils.equals(input, "error")) {
            throw new Exception("抛出了异常");
        }
        demoService.producerExecute("这是第二条消息");
    }

    @ResponseBody
    @GetMapping("test-hello")
    public String testHello() {
        return helloService.sayHello("，琪亚娜");
    }

}
