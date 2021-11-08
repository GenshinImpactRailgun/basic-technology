package com.logsystem.es.springbootdemo.config;

import cn.hutool.core.lang.Assert;
import com.basic.comon.util.reflect.ReflectUtils;
import com.logsystem.es.springbootdemo.property.ElasticsearchProperties;
import com.logsystem.es.springbootdemo.util.ElasticsearchUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author railgun
 * 2021/11/7 14:41
 * es 配置类
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class ElasticsearchConfig {

    /**
     * 2021/11/7 15:07 @railgun es 配置类读取的数据
     */
    private final ElasticsearchProperties elasticsearchProperties;

    /**
     * 2021/11/7 15:06 @railgun 连接的 es 集群
     */
    private static final CopyOnWriteArraySet<HttpHost> HTTP_HOST_SET = new CopyOnWriteArraySet<>();

    /**
     * 2021/11/7 15:09 @railgun 冒号
     */
    private static final String COLON_STR = ":";

    /**
     * 2021/11/7 15:09 @railgun http 请求协议
     */
    private static final String HTTP_STR = "http";

    /**
     * railgun
     * 2021/10/19 23:09
     * PS: RestHighLevelClient 缺失这个 bean 的时候生效
     */
    @Bean
    @ConditionalOnMissingBean
    public RestHighLevelClient restHighLevelClient() {
        Set<String> clusterNodes = elasticsearchProperties.getClusterNodes();
        if (CollectionUtils.isEmpty(clusterNodes)) {
            throw new RuntimeException("es 集群节点不允许为空");
        }
        initHttpHost(clusterNodes);
        return getRestHighLevelClient(RestClient.builder(HTTP_HOST_SET.toArray(new HttpHost[0])), elasticsearchProperties);
    }

    /**
     * 初始化 http 连接
     *
     * @param clusterNodes 集群节点配置
     * @author railgun
     * 2021/11/7 16:06
     */
    private void initHttpHost(Set<String> clusterNodes) {
        try {
            clusterNodes.forEach(node -> {
                String[] parts = StringUtils.split(node, COLON_STR);
                Assert.notNull(parts, "es node is null value");
                Assert.state(2 == parts.length, "es node must be defined as 'host:port'");
                HTTP_HOST_SET.add(new HttpHost(parts[0], Integer.parseInt(parts[1]), HTTP_STR));
            });
        } catch (Exception e) {
            throw new IllegalStateException("节点配置非法，请检查配置", e);
        }
    }

    /**
     * 获取定制的 RestHighLevelClient bean
     *
     * @param builder                 rest builder
     * @param elasticsearchProperties es 配置类信息
     * @return org.elasticsearch.client.RestHighLevelClient
     * @author railgun
     * 2021/11/7 15:13
     */
    private static RestHighLevelClient getRestHighLevelClient(RestClientBuilder builder, ElasticsearchProperties elasticsearchProperties) {
        // Callback used the default {@link RequestConfig} being set to the {@link CloseableHttpClient}【设置各种超时时间，实现可以自动关闭】
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            // 设置连接超时时间
            requestConfigBuilder.setConnectTimeout(elasticsearchProperties.getConnectTimeout());
            // 设置会话超时时间
            requestConfigBuilder.setSocketTimeout(elasticsearchProperties.getSocketTimeout());
            // 设置请求超时时间
            requestConfigBuilder.setConnectionRequestTimeout(elasticsearchProperties.getConnectionRequestTimeout());
            return requestConfigBuilder;
        });
        // Callback used to customize the {@link CloseableHttpClient} instance used by a {@link RestClient} instance
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            // 设置最大连接数量
            httpClientBuilder.setMaxConnTotal(elasticsearchProperties.getMaxConnectTotal());
            // 设置每条路由的最大连接数量
            httpClientBuilder.setMaxConnPerRoute(elasticsearchProperties.getMaxConnectPerRoute());

            // 设置用户密码
            if (StringUtils.isNotBlank(elasticsearchProperties.getUsername()) && StringUtils.isNotBlank(elasticsearchProperties.getPassword())) {
                CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
                credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(elasticsearchProperties.getUsername(), elasticsearchProperties.getPassword()));
                httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            }

            return httpClientBuilder;
        });
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder);
        ReflectUtils.setStaticField(ElasticsearchUtils.class, ElasticsearchUtils.REST_HIGH_LEVEL_CLIENT_FIELD_NAME, restHighLevelClient);
        return restHighLevelClient;
    }

}
