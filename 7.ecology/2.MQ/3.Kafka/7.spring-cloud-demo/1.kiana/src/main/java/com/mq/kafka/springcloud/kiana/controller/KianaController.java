package com.mq.kafka.springcloud.kiana.controller;

import com.mq.kafka.springboot.autoconfigure.dto.kafka.KafkaMessageDto;
import com.mq.kafka.springboot.autoconfigure.service.inter.message.producer.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: railgun
 * 2021/9/19 17:04
 * PS: kiana 控制层
 */
@RestController
@RequestMapping("kiana")
public class KianaController {

    @Autowired
    @Qualifier("producerService")
    private KafkaProducerService producerService;

    /**
     * railgun
     * 2021/9/19 17:06
     * PS: 生产消息
     */
    @GetMapping("produce")
    public void produce() {
        KafkaMessageDto dto = new KafkaMessageDto();
        dto.setTopic("my-topic");
        dto.setPartition(0);
        dto.setKey("railgun");
        dto.setData("你好，琪亚娜");
        producerService.sendMessage(dto);
    }

}
