package com.basic.comon.dto;

import lombok.Data;
import lombok.ToString;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Author: railgun
 * 2021/9/1
 * PS: 延迟消息 dto
 */
@Data
@ToString
public class DelayedMessageDto implements Delayed {

    /**
     * 2021/9/1 @railgun 剩余时间
     */
    private long surplusTime;

    /**
     * 2021/9/1 @railgun 消息内容
     */
    private String content;

    /**
     * 2021/9/1 @railgun 执行比较时间【时间戳】
     */
    private long compareTime;

    public DelayedMessageDto() {
    }

    /**
     * railgun
     * 2021/9/1
     * PS: 构造函数初始化
     */
    public static DelayedMessageDto init(String content, long surplusTime, TimeUnit unit) {
        DelayedMessageDto dto = new DelayedMessageDto();
        dto.setContent(content);
        dto.setSurplusTime(surplusTime);
        dto.setCompareTime(System.currentTimeMillis() + (surplusTime > 0 ? unit.toMillis(surplusTime) : 0));
        return dto;
    }

    /**
     * railgun
     * 2021/9/1
     * PS: 覆写延迟方法
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return compareTime - System.currentTimeMillis();
    }

    /**
     * railgun
     * 2021/9/1
     * PS: 覆写比较方法
     */
    @Override
    public int compareTo(Delayed o) {
        DelayedMessageDto item = (DelayedMessageDto) o;
        long diff = this.compareTime - item.compareTime;
        if (diff <= 0) {
            // 改成>=会造成问题
            return -1;
        } else {
            return 1;
        }
    }

}
