package com.frame.springcloud.providerdept;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author: railgun
 * 2021/7/1 0:22
 * PS: 主启动类
 * EnableEurekaClient       作为 Eureka 客户端注册到 Eureka 注册中心中
 * EnableDiscoveryClient    开启服务发现，不开启该注解好像也能实现
 * EnableCircuitBreaker     开启熔断
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@EnableCircuitBreaker
public class ProviderDeptApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderDeptApplication.class);
    }
}
