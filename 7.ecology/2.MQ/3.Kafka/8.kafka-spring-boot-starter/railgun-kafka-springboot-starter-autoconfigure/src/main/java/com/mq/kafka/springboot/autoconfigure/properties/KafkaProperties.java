package com.mq.kafka.springboot.autoconfigure.properties;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: railgun
 * 2021/9/17 1:01
 * PS: 示例配置
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ConfigurationProperties(prefix = "railgun.kafka")
public class KafkaProperties {

    /**
     * 2021/9/20 20:44 @railgun 监听的主题
     */
    private String topic;

    /**
     * 2021/9/20 20:44 @railgun 消费者消费对应的分组
     */
    private String groupId;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
}
