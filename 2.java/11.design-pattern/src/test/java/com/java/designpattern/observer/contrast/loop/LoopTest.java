package com.java.designpattern.observer.contrast.loop;

import com.basic.comon.util.thread.ThreadUtil;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author: railgun
 * 2021/9/29
 * PS: loop 展示
 */
public class LoopTest {

    /**
     * 2021/6/7 15:12 @railgun IO 型线程池，maxPool 10
     **/
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = ThreadUtil.getPriorMaxPoolThreadPoolWithCustomQueueSize(true, 10);

    public static void main(String[] args) {
        test2();
    }

    /**
     * railgun
     * 2021/9/29
     * PS: 轮询方式实现通知
     */
    public static void test2() {
        VideoProducer videoProducer = new VideoProducer();
        videoProducer.setId(UUID.randomUUID().toString());
        videoProducer.setNickname("十分的红叶");

        ConsumerUser consumerUser1 = new ConsumerUser();
        consumerUser1.setNickname("普通用户1");
        consumerUser1.addVideoProducer(videoProducer);
        THREAD_POOL_EXECUTOR.execute(() -> monitorChange(consumerUser1));

        ConsumerUser consumerUser2 = new ConsumerUser();
        consumerUser2.setNickname("普通用户2");
        consumerUser2.addVideoProducer(videoProducer);
        THREAD_POOL_EXECUTOR.execute(() -> monitorChange(consumerUser2));

        ConsumerUser consumerUser3 = new ConsumerUser();
        consumerUser3.setNickname("普通用户3");
        consumerUser3.addVideoProducer(videoProducer);
        THREAD_POOL_EXECUTOR.execute(() -> monitorChange(consumerUser3));

        for (int i = 0; i < 10; i++) {
            // up 主 上传了一个视频
            videoProducer.uploadVideo();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * railgun
     * 2021/9/29
     * PS: 监听变更
     */
    private static void monitorChange(ConsumerUser consumerUser) {
        while (true) {
            List<VideoProducer> followList = consumerUser.getFollowList();
            Map<String, Integer> producerVideoNumber = consumerUser.getProducerVideoNumber();
            followList.forEach(item -> {
                int newSize = item.getVideoSize();
                int oldSize = producerVideoNumber.getOrDefault(item.getId(), 0);
                if (newSize > oldSize) {
                    // 更新 up 主 视频大小
                    producerVideoNumber.put(item.getId(), newSize);
                    // 发送通知消息
                    consumerUser.onMessage(item);
                }
            });
            try {
                // 轮询遍历逻辑时停 1 秒钟
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
