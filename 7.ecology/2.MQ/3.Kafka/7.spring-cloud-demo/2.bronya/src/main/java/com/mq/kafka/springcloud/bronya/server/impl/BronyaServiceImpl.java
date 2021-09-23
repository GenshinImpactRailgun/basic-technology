package com.mq.kafka.springcloud.bronya.server.impl;

import com.mq.kafka.springboot.autoconfigure.dto.kafka.KafkaMessageDto;
import com.mq.kafka.springboot.autoconfigure.service.inter.message.consumer.KafkaConsumerService;
import com.mq.kafka.springcloud.bronya.server.inter.BronyaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

/**
 * @Author: railgun
 * 2021/9/19 17:15
 * PS: 接口实现类
 */
@Slf4j
@Service
public class BronyaServiceImpl implements BronyaService, KafkaConsumerService {

    @Override
    public void consume(ConsumerRecord<String, String> consumerRecord) {
        log.info("bronya 接收到了消息：{}", consumerRecord);
    }

}
