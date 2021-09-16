package com.mq.kafka.springbootdemo.consumer;

import com.basic.comon.constant.KafkaConstant;
import com.mq.kafka.springbootdemo.Dto.KafkaMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.List;

/**
 * @Author: railgun
 * 2021/9/11 11:33
 * PS: 消费者
 */
@Slf4j
// @Component
public class KafkaConsumerBatch {

    private static Integer count = 0;

    /**
     * railgun
     * 2021/9/12 0:50
     * PS: 消费者消费执行方法【可以使用两个消费者执行消费，完成并发】
     */
    @KafkaListener(topics = KafkaConstant.TEST_TOPIC, groupId = "demo01-consumer-group-" + KafkaConstant.TEST_TOPIC)
    public void onMessage(List<KafkaMessageDto> message) throws Exception {
        count++;
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        log.info("----------------------------------- {} -----------------------------------", count);
        throw new Exception("抛出一个异常");
    }

    /**
     * railgun
     * 2021/9/12 0:50
     * PS: 消费者消费执行方法【可以使用两个消费者执行消费，完成并发】
     */
    @KafkaListener(topics = KafkaConstant.TEST_TOPIC, groupId = "demo01-consumer-group-" + KafkaConstant.TEST_TOPIC + "-kiana")
    public void onMessageKiana(List<ConsumerRecord<Integer, String>> record) {
        log.info("[onMessageKiana][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), record);
    }

}
