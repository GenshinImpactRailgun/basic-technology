package com.mq.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import static org.apache.kafka.clients.producer.ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG;

/**
 * @Author: railgun
 * 2021/9/8 9:47
 * PS: kafka 生产者
 **/
@Slf4j
public class KafkaProducerDemoTests {

    /**
     * railgun
     * 2021/9/8 14:26
     * PS: 配合消费者自动提交的生产者
     * 【这里的 topic 一定需要通过 kafka-topic.sh 创建好，否则会发生消息无法发送的情况】
     * 创建 topic 的脚本：kafka-topics.sh --create --zookeeper 192.168.39.39:2181,192.168.39.40:2181,192.168.39.41:2181/kafka --replication-factor 3 --partitions 1 --topic my-topic
     * 【注释：集群的 leader 测试情况中如果不是 192.168.39.39:9092 会发生发送消息失败的问题，我也不知道为啥】
     * 生产者是线程安全的【单实例生产者比多实例生产者更加高效】
     */
    @Test
    public void test1() {
        Properties props = new Properties();
        // kafka 集群服务地址【也可以设置单机模式】【如果不是事先创建好的 topic 就可以用单机的模式】
        // props.setProperty("bootstrap.servers", "192.168.39.39:9092");
        props.setProperty("bootstrap.servers", "192.168.39.39:9092,192.168.39.40:9092,192.168.39.41:9092");
        // 该配置用作集群中多少节点确认之后，返回确认消息【0：不需要任何节点返回确认消息；1：只需要 Leader 节点返回确认消息；-1 或 all：需要全部 ISR 节点集合返回确认消息】
        props.put("acks", "all");
        // 如果发送失败，尝试重新发送的次数
        props.put("retries", 0);
        // 延迟发送时间【send 方法调用之后会停顿一段时间；这将指示生产者在发送请求之前等待该毫秒数，希望更多记录将到达以填充同一批】【这类似于TCP中的Nagle算法】
        props.put("linger.ms", 1);
        // 指定 key 的序列化方式
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        // 指定 value 的序列化方式
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 100; i++) {
            // topic key value
            producer.send(new ProducerRecord<>("my-topic", Integer.toString(i), Integer.toString(i)));
        }
        // 关闭 producer
        producer.close();
    }

    /**
     * railgun
     * 2021/9/9 22:43
     * PS: TODO 这里的一段概念比较模糊，需要好好整理一下
     * 事务生产者【如果设置了transactional.id，则幂等性将自动启用，同时生产者将配置幂等性所依赖的】
     * 【要启用幂等性，必须将enable.幂等性配置设置为true。如果设置，重试配置将默认为Integer.MAX_值，acks配置将默认为all】
     * 【每个生产者只能有一个开放事务。在beginTransaction（）和commitTransaction（）调用之间发送的所有消息都将是单个事务的一部分】
     * 【事务生产者使用异常来传递错误状态】
     * 【特别是，不需要为producer.send（）或对返回的将来调用.get（）指定回调：如果producer.send（）或事务调用在事务期间遇到不可恢复的错误，将引发KafkaException】
     * 【通过在收到KafkaException时调用producer.abortTransaction（），我们可以确保任何成功的写入都被标记为已中止，从而保持事务性保证】
     * 【官方示例实践失败】
     * 【幂等的生产者模式】【事务的生产者模式】
     */

    /**
     * railgun
     * 2021/9/9 22:44
     * PS: 幂等的生产者模式
     */
    @Test
    public void test2() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.39.39:9092,192.168.39.40:9092,192.168.39.41:9092");
        props.put(ENABLE_IDEMPOTENCE_CONFIG, true);
        // 开始幂等性之后，acks 参数会自动设置成 all
        props.put("acks", "all");
        // 开启幂等性之后，重试次数会被设置成 Integer.MAX_VALUE
        props.put("retries", Integer.MAX_VALUE);
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        // 发送第一遍
        for (int i = 0; i < 1; i++) {
            producer.send(new ProducerRecord<>("my-topic", Integer.toString(i), Integer.toString(i)));
        }
        // 发送第二遍
        for (int i = 0; i < 1; i++) {
            producer.send(new ProducerRecord<>("my-topic", Integer.toString(i), Integer.toString(i)));
        }
        producer.close();
    }

    /**
     * railgun
     * 2021/9/9 22:42
     * PS: 事务的生产者模式
     * 【一定得开启日志，emmmm，太坑了】
     * 【advertised.listeners=PLAINTEXT://192.168.39.40:9092】【该项内容一定要设置为服务器的 ip + 端口号，不然其他远程主机访问会有域名访问异常】
     */
    @Test
    public void test3() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.39.39:9092,192.168.39.40:9092,192.168.39.41:9092");
        props.put("transactional.id", "my-transactional-id");
        Producer<String, String> producer = new KafkaProducer<>(props, new StringSerializer(), new StringSerializer());

        producer.initTransactions();

        try {
            producer.beginTransaction();
            for (int i = 0; i < 100; i++)
                producer.send(new ProducerRecord<>("my-topic", Integer.toString(i), Integer.toString(i)));
            producer.commitTransaction();
        } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
            // We can't recover from these exceptions, so our only option is to close the producer and exit.
            producer.close();
        } catch (KafkaException e) {
            // For all other exceptions, just abort the transaction and try again.
            producer.abortTransaction();
        }
        producer.close();
    }

    /**
     * railgun
     * 2021/9/9 22:50
     * PS: 模拟简单的阻塞调用
     */
    @Test
    public void test4() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.39.39:9092,192.168.39.40:9092,192.168.39.41:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        try {
            for (int i = 0; i < 1; i++) {
                // 这个 send 方法执行之后会发生阻塞
                producer.send(new ProducerRecord<>("my-topic", Integer.toString(i), Integer.toString(i))).get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        producer.close();
    }

    /**
     * railgun
     * 2021/9/9 22:47
     * PS: send 发送之后 执行回调方法
     */
    @Test
    public void test5() {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "192.168.39.39:9092,192.168.39.40:9092,192.168.39.41:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("linger.ms", 1);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 1; i++) {
            producer.send(new ProducerRecord<>("my-topic", Integer.toString(i), Integer.toString(i)), (metadata, e) -> {
                System.out.println("回调方法执行");
                if (e != null) {
                    System.out.println("send 失败");
                    e.printStackTrace();
                } else {
                    System.out.println("send 成功");
                    System.out.println("The offset of the record we just sent is: " + metadata.offset());
                }
            });

        }
        producer.close();
    }

}
