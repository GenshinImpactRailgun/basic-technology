package com.mq.kafka.springboot.autoconfigure.service.impl.message.consumer;

import com.mq.kafka.springboot.autoconfigure.dto.kafka.KafkaMessageDto;
import com.mq.kafka.springboot.autoconfigure.service.inter.message.consumer.KafkaConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

/**
 * @Author: railgun
 * 2021/9/19 17:53
 * PS: kafka 消费者接口实现类
 */
@Slf4j
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @Override
    public void consume(ConsumerRecord<String, String> consumerRecord) {
        log.info("本不应该接收到消息的方法，接收到了消息：{}", consumerRecord);
    }

}
