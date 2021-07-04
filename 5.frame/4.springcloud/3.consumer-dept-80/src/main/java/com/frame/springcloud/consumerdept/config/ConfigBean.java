package com.frame.springcloud.consumerdept.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: railgun
 * 2021/7/4 13:08
 * PS: 注册 bean
 **/
@Configuration
public class ConfigBean {

    /**
     * railgun
     * 2021/7/4 15:46
     * PS: LoadBalanced：配置负载均衡实现
     * IRule 负载均衡策略
     * AvailabilityFilteringRule：先过滤跳闸的服务，再轮询
     **/
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    /**
     * railgun
     * 2021/7/4 16:50
     * PS: 选择自己想要的负载均衡策略
     * 【如果该类在默认扫描路径下，那么会覆盖所有的负载均衡策略】
     * 【如果想要给某一个服务指定负载均衡策略，那么就应该放到其他目录层级中】
     **/
    @Bean
    public IRule getRule() {
        return new RailgunRule();
    }

}
