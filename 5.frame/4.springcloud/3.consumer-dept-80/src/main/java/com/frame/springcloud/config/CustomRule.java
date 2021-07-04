package com.frame.springcloud.config;

import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: railgun
 * 2021/7/4 16:58
 * PS: 自定义负载均衡策略
 * 【如果该类在默认扫描路径下，那么会覆盖所有的负载均衡策略】
 * 【如果想要给某一个服务指定负载均衡策略，那么就应该放到其他目录层级中】
 **/
@Configuration
public class CustomRule {

    /**
     * railgun
     * 2021/7/4 17:19
     * PS: 给指定服务指定负载均衡访问策略
     **/
    @Bean
    public IRule getRule() {
        // 负载均衡策略为，存活的的应用中轮询
        return new AvailabilityFilteringRule();
    }

}
