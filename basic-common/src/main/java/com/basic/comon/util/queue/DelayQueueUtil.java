package com.basic.comon.util.queue;

import com.basic.comon.concurrent.KafkaConsumerRunner;
import com.basic.comon.dto.DelayedMessageDto;
import com.basic.comon.util.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: railgun
 * 2021/9/1
 * PS: 延迟队列工具类
 */
public class DelayQueueUtil {

    /**
     * 2021/6/7 15:12 @railgun IO 型线程池，maxPool 10
     **/
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = ThreadUtil.getPriorMaxPoolThreadPoolWithCustomQueueSize(false, 10);

    /**
     * 2021/9/1 @railgun 延迟队列
     */
    private static final DelayQueue<DelayedMessageDto> DELAY_QUEUE = new DelayQueue<>();

    /**
     * 2021/9/1 @railgun 当前生效的 WebSocket key
     */
    public static final CopyOnWriteArrayList<String> EFFECT_WEB_SOCKET_KEY = new CopyOnWriteArrayList<>();

    /**
     * 2021/9/2 @railgun 强制退出集合
     */
    public static final CopyOnWriteArraySet<String> FORCE_RE_LOGIN = new CopyOnWriteArraySet<>();

    /**
     * railgun
     * 2021/9/1
     * PS: 塞入延迟任务
     */
    public static void putToShutdownConsumer(DelayedMessageDto dto, KafkaConsumerRunner kafkaConsumerRunner) {
        if (DELAY_QUEUE.isEmpty()) {
            // 延迟队列为空的情况下，塞入任务之后，塞入遍历延迟队列的方法到线程池中
            DELAY_QUEUE.put(dto);
            // 塞入遍历延迟队列的方法到线程池中执行
            THREAD_POOL_EXECUTOR.execute(() -> executeShutdownConsumer(kafkaConsumerRunner));
        } else {
            DELAY_QUEUE.put(dto);
        }
    }

    /**
     * railgun
     * 2021/9/8 23:01
     * PS: 执行停止方法
     */
    private static void executeShutdownConsumer(KafkaConsumerRunner kafkaConsumerRunner) {
        try {
            while (!DELAY_QUEUE.isEmpty()) {
                // 延时队列阻塞获取，到时间之后弹出最近时间的对象；
                DelayedMessageDto dto = DELAY_QUEUE.take();
                System.out.printf("【executeShutdownConsumer】【对象内容：%s】%n", dto);
                kafkaConsumerRunner.shutdown();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
