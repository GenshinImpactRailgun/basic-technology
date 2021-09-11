package com.mq.kafka.springbootdemo;

import com.mq.kafka.springbootdemo.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;

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

    @Test
    public void testSyncSend() {
        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult<String, Object> result = producer.syncSend(String.valueOf(id), "hello kiana");
        log.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);

        try {
            // 阻塞等待，保证消费
            new CountDownLatch(1).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testASyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.asyncSend(String.valueOf(id), "bronya").addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable e) {
                log.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, e);
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, result);
            }
        });
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

}
