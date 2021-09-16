package com.mq.kafka.springbootdemo.consumer;

import com.basic.comon.constant.KafkaConstant;
import com.mq.kafka.springbootdemo.Dto.KafkaMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @Author: railgun
 * 2021/9/11 11:33
 * PS: 消费者
 */
@Slf4j
@Component
public class KafkaConsumer {

    /**
     * railgun
     * 2021/9/12 0:50
     * PS: 消费者消费执行方法【可以使用两个消费者执行消费，完成并发】
     */
    // @KafkaListener(topics = KafkaConstant.TEST_TOPIC, groupId = "demo01-consumer-group-" + KafkaConstant.TEST_TOPIC)
    public void onMessage(KafkaMessageDto message) throws Exception {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        throw new Exception("抛出一个异常");
    }

    /**
     * railgun
     * 2021/9/12 0:50
     * PS: 消费者消费执行方法【可以使用两个消费者执行消费，完成并发】
     */
    // @KafkaListener(topics = KafkaConstant.TEST_TOPIC, groupId = "demo01-consumer-group-" + KafkaConstant.TEST_TOPIC + "-kiana")
    public void onMessageKiana(ConsumerRecord<Integer, String> record) {
        log.info("[onMessageKiana][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), record);
    }

    /**
     * railgun
     * 2021/9/13 0:52
     * PS: 单应用多实例实现广播消费
     */
    // @KafkaListener(topics = KafkaConstant.TEST_TOPIC, groupId = "demo01-consumer-group-" + KafkaConstant.TEST_TOPIC + "-" + "#{T(java.util.UUID).randomUUID()}")
    public void onMessageUuid(ConsumerRecord<Integer, String> record) {
        log.info("[onMessageUuid][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), record);
    }

    /**
     * railgun
     * 2021/9/13 9:01
     * PS: consumer 同一 topic 不同 partition 下面的并发消费
     */
    // @KafkaListener(topics = KafkaConstant.TEST_TOPIC, groupId = "demo01-consumer-group-" + KafkaConstant.TEST_TOPIC + "-" + "#{T(java.util.UUID).randomUUID()}", concurrency = "4")
    public void onMessageConcurrent(ConsumerRecord<Integer, String> record) {
        log.info("[onMessageConcurrent][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), record);
    }

    private static Integer count = 0;

    /**
     * railgun
     * 2021/9/14 1:01
     * PS: 此时就算不执行 Acknowledge 的 acknowledge 方法，也会由 spring 来提交偏移量，并不会不提交偏移量
     * spring.kafka.consumer.enable-auto-commit 为 true kafka 完成偏移量提交，为 false spring 完成偏移量提交
     * 所以并不会如教程所说，偏移量不会改变，偏移量会自动改变，因为有 spring 自动提交了，简化了代码量
     */
    @KafkaListener(topics = KafkaConstant.TEST_TOPIC, groupId = "demo01-consumer-group-" + KafkaConstant.TEST_TOPIC + "-" + "#{T(java.util.UUID).randomUUID()}", concurrency = "4")
    public void onMessageConcurrent(ConsumerRecord<Integer, String> record, Acknowledgment acknowledgment) {
        log.info("[onMessageConcurrent][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), record);
        count++;
        // 每隔两条消息就提交一个消息
        if (count <= 50) {
            acknowledgment.acknowledge();
        }
    }

}
