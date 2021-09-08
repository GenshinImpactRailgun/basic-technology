package com.basic.comon.concurrent;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Author: railgun
 * 2021/9/8 22:03
 * PS: consumer 线程执行用例
 */
public class KafkaConsumerRunner implements Runnable {

    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final KafkaConsumer<String, String> consumer;

    public KafkaConsumerRunner(KafkaConsumer<String, String> consumer) {
        this.consumer = consumer;
    }

    @Override
    public void run() {
        try {
            while (!closed.get()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(Long.MAX_VALUE));
                for (TopicPartition partition : records.partitions()) {
                    // 按照分区处理数据
                    List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                    for (ConsumerRecord<String, String> record : partitionRecords) {
                        System.out.println(record.offset() + ": " + record.value());
                    }
                    long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                    // 如果在提交偏移量之前程序中断了，那么下一次启动消费者的时候，会把最后一个批次的消息再次消费一遍
                    consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
                }
            }
        } catch (WakeupException e) {
            System.out.println("consumer 抛出异常");
            // Ignore exception if closing
            if (!closed.get()) {
                throw e;
            }
        } finally {
            System.out.println("consumer close 执行");
            consumer.close();
            System.out.println("consumer close 结束");
        }
    }

    /**
     * railgun
     * 2021/9/8 22:04
     * PS: Shutdown hook which can be called from a separate thread
     */
    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }

}
