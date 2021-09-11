package com.mq.kafka.springbootdemo.consumer;

import com.basic.comon.constant.KafkaConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @Author: railgun
 * 2021/9/11 11:33
 * PS:
 */
@Slf4j
@Component
public class KafkaConsumerKiana {

    @KafkaListener(topics = KafkaConstant.TEST_TOPIC, groupId = "demo01-consumer-group-" + KafkaConstant.TEST_TOPIC + "-kiana")
    public void onMessage(ConsumerRecord<Integer, String> record) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), record);
    }

}
