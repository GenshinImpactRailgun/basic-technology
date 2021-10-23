package com.logsystem.es.springbootdemo.config;

import cn.hutool.core.lang.Assert;
import com.logsystem.es.springbootdemo.property.ElasticsearchProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author: railgun
 * 2021/10/19 23:07
 * PS: es 配置类
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ElasticsearchProperties.class)
public class ElasticsearchConfig {

    private final ElasticsearchProperties elasticsearchProperties;

    private final List<HttpHost> httpHosts = new ArrayList<>();

    /**
     * railgun
     * 2021/10/19 23:09
     * PS: RestHighLevelClient 缺失这个 bean 的时候生效
     */
    @Bean
    @ConditionalOnMissingBean
    public RestHighLevelClient restHighLevelClient() {
        List<String> clusterNodes = elasticsearchProperties.getClusterNodes();
        log.info("配置的集群节点数据为：{}", clusterNodes.toString());
        if (clusterNodes.isEmpty()) {
            throw new RuntimeException("集群节点不允许为空");
        }
        clusterNodes.forEach(item -> {
            try {
                String[] parts = StringUtils.split(item, ":");
                Assert.notNull(parts, "Must defined");
                Assert.state(parts.length == 2, "Must be defined as 'host:port'");
                httpHosts.add(new HttpHost(parts[0], Integer.parseInt(parts[1]), elasticsearchProperties.getSchema()));
            } catch (Exception e) {
                throw new IllegalStateException("Invalid ES nodes " + "property '" + item + "'", e);
            }
        });
        // list 转换为数组，构建 RestClientBuilder
        RestClientBuilder builder = RestClient.builder(httpHosts.toArray(new HttpHost[0]));
        return getRestHighLevelClient(builder, elasticsearchProperties);
    }

    /**
     * railgun
     * 2021/10/19 23:10
     * PS: 获取定制的 RestHighLevelClient bean
     */
    private static RestHighLevelClient getRestHighLevelClient(RestClientBuilder builder, ElasticsearchProperties elasticsearchProperties) {
        // Callback used the default {@link RequestConfig} being set to the {@link CloseableHttpClient}
        // 回调使用默认的 {@link RequestConfig} 设置为 {@link CloseableHttpClient}
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(elasticsearchProperties.getConnectTimeout());
            requestConfigBuilder.setSocketTimeout(elasticsearchProperties.getSocketTimeout());
            requestConfigBuilder.setConnectionRequestTimeout(elasticsearchProperties.getConnectionRequestTimeout());
            return requestConfigBuilder;
        });
        // Callback used to customize the {@link CloseableHttpClient} instance used by a {@link RestClient} instance.
        // 回调用于自定义 {@link RestClient} 实例使用的 {@link CloseableHttpClient} 实例。
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(elasticsearchProperties.getMaxConnectTotal());
            httpClientBuilder.setMaxConnPerRoute(elasticsearchProperties.getMaxConnectPerRoute());
            return httpClientBuilder;
        });
        // Callback used the basic credential auth
        // 回调使用了基本的凭证认证
        ElasticsearchProperties.Account account = elasticsearchProperties.getAccount();
        if (StringUtils.isNotBlank(account.getUsername()) && StringUtils.isNotBlank(account.getUsername())) {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(account.getUsername(), account.getPassword()));
        }
        return new RestHighLevelClient(builder);
    }


}
