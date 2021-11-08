package com.logsystem.es.springbootdemo.property;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashSet;
import java.util.Set;

/**
 * @author railgun
 * 2021/11/7 14:59
 * es 配置类
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticsearchProperties {

    /**
     * 2021/11/7 15:29 @railgun es 集群节点【例如：192.168.39.39:9200】
     */
    private Set<String> clusterNodes = new HashSet<>();

    /**
     * 2021/11/7 15:31 @railgun 用户名
     */
    private String username;

    /**
     * 2021/11/7 15:32 @railgun 密码
     */
    private String password;

    /**
     * 2021/11/7 15:32 @railgun 连接超时时间(毫秒)
     */
    private Integer connectTimeout = 10000;

    /**
     * 2021/11/7 15:32 @railgun socket 超时时间
     */
    private Integer socketTimeout = 30000;

    /**
     * 2021/11/7 15:32 @railgun 连接请求超时时间
     */
    private Integer connectionRequestTimeout = 500;

    /**
     * 2021/11/7 15:32 @railgun 每个路由的最大连接数量
     */
    private Integer maxConnectPerRoute = 10;

    /**
     * 2021/11/7 15:32 @railgun 最大连接总数量
     */
    private Integer maxConnectTotal = 30;

    /**
     * 2021/11/7 15:33 @railgun 索引配置信息
     */
    private final Index index = new ElasticsearchProperties.Index();

    /**
     * @author railgun
     * 2021/11/7 15:33
     * 索引配置信息
     */
    @Data
    @Accessors(chain = true)
    @RequiredArgsConstructor
    public static class Index {

        /**
         * 2021/11/7 15:35 @railgun 分片数量
         */
        private Integer numberOfShards = 3;

        /**
         * 2021/11/7 15:35 @railgun 副本数量
         */
        private Integer numberOfReplicas = 0;

    }

}
