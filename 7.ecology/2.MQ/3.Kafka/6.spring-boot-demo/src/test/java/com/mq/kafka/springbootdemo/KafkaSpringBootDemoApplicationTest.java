package com.mq.kafka.springbootdemo;

import com.mq.kafka.springbootdemo.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

/**
 * @Author: railgun
 * 2021/9/11 11:01
 * PS:
 */
@Slf4j
@SpringBootTest(classes = KafkaSpringBootDemoApplication.class)
public class KafkaSpringBootDemoApplicationTest {

    @Autowired
    private KafkaProducer producer;

    /**
     * railgun
     * 2021/9/12 15:17
     * PS: 发送同步消息
     */
    @Test
    public void testSyncSend() {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult<String, Object> result = producer.syncSend(String.valueOf(id), "你好，琪亚娜");
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
    }

    /**
     * railgun
     * 2021/9/12 15:18
     * PS: 发送异步消息，并且执行回调
     */
    @Test
    public void testASyncSend() {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.asyncSend(String.valueOf(id), "你好，布洛妮娅").addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable e) {
                log.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, e);
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, result);
            }
        });
    }

    /**
     * railgun
     * 2021/9/12 15:21
     * PS: 执行批量发送消息的测试。批量发送消息由三个参考控制：batch.size、buffer.memory、linger.ms
     * kafka 中并不存在单独的批量的概念，因为 kafka 本身就是批量发送消息的。
     * producer 发送消息给 broker 之前会停顿一定时间，是一种减少 IO 次数，提升效率的策略
     * kafkaTemplate flush 调用之后可以立即发送 消息 给 broker，但是此次会话之后的消息就都不能发送了
     */
    @Test
    public void sendBatch() {
        int num = 1;
        for (int i = 0; i < num; i++) {
            producer.asyncSend("railgun-uuid-" + i, "你好，琪亚娜，第 " + i + " 次");
        }
    }

    /**
     * railgun
     * 2021/9/13 9:36
     * PS: 测试并发消费消息
     */
    @Test
    public void testSendConcurrent() {
        int num = 40;
        for (int i = 1; i <= num; i++) {
            // producer.asyncSend("railgun-uuid-" + i, "你好，琪亚娜，第 " + i + " 次");
            // producer.sendMessageToAppointPartitionByKey("railgun-uuid-" + i, "你好，琪亚娜，第 " + i + " 次");
            producer.sendMessageToAppointPartitionByPartition("railgun-uuid-" + i, "你好，琪亚娜，第 " + i + " 次");
        }
    }

    @Test
    public void testSyncSendInTransaction() throws ExecutionException, InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSendInTransaction(id, "你好，芽衣", () -> {
            log.info("[run][我要开始睡觉了]");
            try {
                Thread.sleep(10 * 1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info("[run][我睡醒了]");
        });
    }

}
