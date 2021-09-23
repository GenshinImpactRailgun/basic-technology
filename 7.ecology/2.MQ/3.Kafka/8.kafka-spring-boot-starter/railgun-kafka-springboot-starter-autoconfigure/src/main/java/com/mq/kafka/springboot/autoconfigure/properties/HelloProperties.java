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
@ToString
@NoArgsConstructor
@Accessors(chain = true)
@ConfigurationProperties(prefix = "railgun.hello")
public class HelloProperties {

    /**
     * 2021/9/17 1:02 @railgun 前缀
     */
    private String prefix;

    /**
     * 2021/9/17 1:03 @railgun 后缀
     */
    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
