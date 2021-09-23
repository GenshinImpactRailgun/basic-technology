package com.mq.kafka.springboot.autoconfigure.service.impl.message.producer;

import com.mq.kafka.springboot.autoconfigure.dto.kafka.KafkaMessageDto;
import com.mq.kafka.springboot.autoconfigure.exception.KafkaCustomException;
import com.mq.kafka.springboot.autoconfigure.service.inter.message.producer.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author: railgun
 * 2021/9/19 22:45
 * PS: 声明第二种接口实现类用作区分
 */
@Slf4j
public class KafkaProducerServiceTwoImpl implements KafkaProducerService {

    @Override
    public void sendMessage(KafkaMessageDto kafkaMessageDto) throws KafkaCustomException {
        log.info("使用第二种接口发送消息");
    }

    @Override
    public void sendMessageTransactional(KafkaMessageDto kafkaMessageDto, Runnable runner) {
        log.info("使用第二种接口发送消息");
    }
}
