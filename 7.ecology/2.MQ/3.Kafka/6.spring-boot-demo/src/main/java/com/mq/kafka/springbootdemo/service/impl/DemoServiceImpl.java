package com.mq.kafka.springbootdemo.service.impl;

import com.mq.kafka.springbootdemo.producer.KafkaProducer;
import com.mq.kafka.springbootdemo.service.inter.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: railgun
 * 2021/9/11 11:05
 * PS:
 */
@Slf4j
@Service
public class DemoServiceImpl implements DemoService {

    private final KafkaProducer kafkaProducer;

    public DemoServiceImpl(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    /**
     * railgun
     * 2021/9/11 11:09
     * PS: send message
     */
    @Override
    public void producerExecute(String message) {
        log.info("message:{}", message);
        kafkaProducer.sendMessageToAppointPartitionByPartition("1", message);
    }

}
