package com.mq.kafka.consumer;

import com.basic.comon.concurrent.KafkaConsumerRunner;
import com.basic.comon.dto.DelayedMessageDto;
import com.basic.comon.util.queue.DelayQueueUtil;
import com.basic.comon.util.thread.ThreadUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: railgun
 * 2021/9/8 10:05
 * PS: kafka 消费者
 */
public class KafkaConsumerDemoTests {

    /**
     * 2021/9/8 22:22 @railgun max pool 优先的线程池，core 4 queue 100 max 10
     */
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = ThreadUtil.getPriorMaxPoolThreadPool(true);

    /**
     * railgun
     * 2021/9/8 12:30
     * PS: 自动提交
     */
    @Test
    public void test1() {
        // 配置信息
        Properties props = new Properties();
        // kafka 集群服务地址【也可以设置单机模式】
        // props.setProperty("bootstrap.servers", "192.168.39.39:9092");
        props.setProperty("bootstrap.servers", "192.168.39.39:9092,192.168.39.40:9092,192.168.39.41:9092");
        // 消费者组标签，必须设置
        props.setProperty("group.id", "consumer-group-id-1");
        // 指定了 offset 方式为自动提交
        props.setProperty("enable.auto.commit", "true");
        // 每次自动提交的时间间隔
        props.setProperty("auto.commit.interval.ms", "1000");
        // 指定 key 序列化方式
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 指定 value 序列化方式
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // consumer 是有状态的，不是线程安全的，必须每一个线程实例化一个 consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // 订阅的 topic list
        consumer.subscribe(Arrays.asList("my-topic"));
        boolean running = true;
        int count = 0;
        while (running) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                count++;
                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
            }
            if (count >= 200) {
                // 满足一定业务条件之后一定要关闭 consumer
                running = false;
            }
        }
        consumer.close();
    }

    /**
     * railgun
     * 2021/9/8 12:30
     * PS: 手动提交，手动控制偏移量
     */
    @Test
    public void test2() {
        // 配置信息
        Properties props = new Properties();
        // kafka 集群服务地址【也可以设置单机模式】
        // props.setProperty("bootstrap.servers", "192.168.39.39:9092");
        props.setProperty("bootstrap.servers", "192.168.39.39:9092,192.168.39.40:9092,192.168.39.41:9092");
        // 消费者组标签，必须设置
        props.setProperty("group.id", "consumer-group-id-1");
        // 指定了 offset 方式为手动添提交
        props.setProperty("enable.auto.commit", "false");
        // 每次自动提交的时间间隔
        props.setProperty("auto.commit.interval.ms", "1000");
        // 指定 key 序列化方式
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 指定 value 序列化方式
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // consumer 是有状态的，不是线程安全的，必须每一个线程实例化一个 consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // 订阅的 topic list
        consumer.subscribe(Arrays.asList("my-topic"));
        final int minBatchSize = 20;
        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        boolean running = true;
        int count = 0;
        while (running) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                buffer.add(record);
            }
            if (buffer.size() >= minBatchSize) {
                count += buffer.size();
                // 执行入库方法
                insertIntoDb(buffer);
                // 手动提交偏移量
                consumer.commitSync();
                // 提交一次之后，清空 buffer 缓冲区
                buffer.clear();
            }
            if (count >= 200) {
                // 满足一定业务条件之后一定要关闭 consumer
                running = false;
            }
        }
        consumer.close();
    }

    /**
     * railgun
     * 2021/9/8 21:41
     * PS: 处理完每个分区中的记录后，提交 offset（偏移量）
     */
    @Test
    public void test3() {
        // 配置信息
        Properties props = new Properties();
        // kafka 集群服务地址【也可以设置单机模式】
        // props.setProperty("bootstrap.servers", "192.168.39.39:9092");
        props.setProperty("bootstrap.servers", "192.168.39.39:9092,192.168.39.40:9092,192.168.39.41:9092");
        // 消费者组标签，必须设置
        props.setProperty("group.id", "consumer-group-id-1");
        // 指定了 offset 方式为手动添提交
        props.setProperty("enable.auto.commit", "false");
        // 每次自动提交的时间间隔
        props.setProperty("auto.commit.interval.ms", "1000");
        // 指定 key 序列化方式
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 指定 value 序列化方式
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // consumer 是有状态的，不是线程安全的，必须每一个线程实例化一个 consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // 订阅的 topic list
        consumer.subscribe(Arrays.asList("my-topic"));
        try {
            boolean running = true;
            int count = 0;
            while (running) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(Long.MAX_VALUE));
                for (TopicPartition partition : records.partitions()) {
                    // 按照分区处理数据
                    List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                    for (ConsumerRecord<String, String> record : partitionRecords) {
                        count++;
                        System.out.println(record.offset() + ": " + record.value());
                    }
                    long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                    // 如果在提交偏移量之前程序中断了，那么下一次启动消费者的时候，会把最后一个批次的消息再次消费一遍
                    consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
                }
                if (count >= 200) {
                    // 满足一定业务条件之后一定要关闭 consumer
                    running = false;
                }
            }
        } finally {
            consumer.close();
        }
    }

    /**
     * railgun
     * 2021/9/8 23:21
     * PS: 函数主方法
     */
    public static void main(String[] args) {
        // 涉及到子线程的操作，只能放到主方法中
        test4();
    }

    /**
     * railgun
     * 2021/9/8 22:07
     * PS: 通过 wakeup 方法阻断另一线程中的 consumer，从而停止这个 consumer
     * consumer 的消费逻辑放到线程池的子线程方法中执行；【线程操作使用 ThreadUtil】
     * 另一个停止 consumer 的线程放到延时队列中使用线程池子线程的方式执行【延时队列使用 DelayQueueUtil 操作】
     */
    public static void test4() {
        // 配置信息
        Properties props = new Properties();
        // kafka 集群服务地址【也可以设置单机模式】
        // props.setProperty("bootstrap.servers", "192.168.39.39:9092");
        props.setProperty("bootstrap.servers", "192.168.39.39:9092,192.168.39.40:9092,192.168.39.41:9092");
        // 消费者组标签，必须设置
        props.setProperty("group.id", "consumer-group-id-1");
        // 指定了 offset 方式为手动添提交
        props.setProperty("enable.auto.commit", "false");
        // 每次自动提交的时间间隔
        props.setProperty("auto.commit.interval.ms", "1000");
        // 指定 key 序列化方式
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // 指定 value 序列化方式
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        // consumer 是有状态的，不是线程安全的，必须每一个线程实例化一个 consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        // 订阅的 topic list
        consumer.subscribe(Collections.singletonList("my-topic"));

        KafkaConsumerRunner kafkaConsumerRunner = new KafkaConsumerRunner(consumer);
        // 子线程执行消费方法
        THREAD_POOL_EXECUTOR.execute(kafkaConsumerRunner);
        // 另外一个子线程停止上一个子线程中的消费方法【使用延迟队列，执行子线程的方式】
        DelayQueueUtil.putToShutdownConsumer(DelayedMessageDto.init("my-topic", 30, TimeUnit.SECONDS), kafkaConsumerRunner);
    }

    /**
     * railgun
     * 2021/9/8 12:36
     * PS: 将 kafka 获取的 list 的数据执行滚批量入库
     */
    private void insertIntoDb(List<ConsumerRecord<String, String>> buffer) {
        buffer.forEach(record -> {
            System.out.printf("数据库入库记录，ConsumerRecord：offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
        });
    }

}
