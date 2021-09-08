package com.mq.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;

import java.util.Properties;

/**
 * @Author: railgun
 * 2021/9/8 9:47
 * PS: kafka 生产者
 **/
public class KafkaProducerDemoTests {

    /**
     * railgun
     * 2021/9/8 14:26
     * PS: 配合消费者自动提交的生产者
     * 【这里的 topic 一定需要通过 kafka-topic.sh 创建好，否则会发生消息无法发送的情况】
     * 创建 topic 的脚本：kafka-topics.sh --create --zookeeper 192.168.39.39:2181,192.168.39.40:2181,192.168.39.41:2181/kafka --replication-factor 3 --partitions 1 --topic my-topic
     **/
    @Test
    public void test1(){
        Properties props = new Properties();
        // kafka 集群服务地址【也可以设置单机模式】【如果不是事先创建好的 topic 就可以用单机的模式】
        // props.setProperty("bootstrap.servers", "192.168.39.39:9092");
        props.setProperty("bootstrap.servers", "192.168.39.39:9092,192.168.39.40:9092,192.168.39.41:9092");
        // 该配置用作集群中多少节点确认之后，返回确认消息【0：不需要任何节点返回确认消息；1：只需要 Leader 节点返回确认消息；-1 或 all：需要全部 ISR 节点集合返回确认消息】
        props.put("acks", "1");
        // 如果发送失败，尝试重新发送的次数
        props.put("retries", 0);
        // 延迟发送时间
        props.put("linger.ms", 1);
        // 指定 key 的序列化方式
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 指定 value 的序列化方式
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 100; i++){
            // topic key value
            producer.send(new ProducerRecord<>("my-topic", Integer.toString(i), Integer.toString(i)));
        }
        // 关闭 producer
        producer.close();
    }

}
