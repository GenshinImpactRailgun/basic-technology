package com.logsystem.es.springbootdemo.property;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: railgun
 * 2021/10/19 23:04
 * PS: es 配置类
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticsearchProperties {

    /**
     * 2021/10/23 railgun 请求协议
     */
    private String schema = "http";

    /**
     * 2021/10/23 railgun 集群名称
     */
    private String clusterName = "elasticsearch";

    /**
     * 2021/10/23 railgun 集群节点
     */
    private List<String> clusterNodes = new ArrayList<>();

    /**
     * 2021/10/23 railgun 连接超时时间(毫秒)
     */
    private Integer connectTimeout = 1000;

    /**
     * 2021/10/23 railgun socket 超时时间
     */
    private Integer socketTimeout = 30000;

    /**
     * 2021/10/23 railgun 连接请求超时时间
     */
    private Integer connectionRequestTimeout = 500;

    /**
     * 2021/10/23 railgun 每个路由的最大连接数量
     */
    private Integer maxConnectPerRoute = 10;

    /**
     * 2021/10/23 railgun 最大连接总数量
     */
    private Integer maxConnectTotal = 30;

    /**
     * 2021/10/23 railgun 索引配置信息
     */
    private Index index = new Index();

    /**
     * 2021/10/23 railgun 认证账户
     */
    private Account account = new Account();


    /**
     * railgun
     * 2021/10/23
     * 索引配置信息
     */
    @Data
    public static class Index {
        /**
         * 2021/10/23 railgun 分片数量
         */
        private Integer numberOfShards = 3;

        /**
         * 2021/10/23 railgun 副本数量
         */
        private Integer numberOfReplicas = 0;
    }

    /**
     * railgun
     * 2021/10/23
     * 认证账户【静态内部类】
     */
    @Data
    public static class Account {
        /**
         * 2021/10/23 railgun 认证用户
         */
        private String username;

        /**
         * 2021/10/23 railgun 认证密码
         */
        private String password;
    }

}
