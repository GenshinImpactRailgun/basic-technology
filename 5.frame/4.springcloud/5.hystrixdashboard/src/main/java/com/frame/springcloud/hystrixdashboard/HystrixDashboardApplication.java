package com.frame.springcloud.hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Author: railgun
 * 2021/7/5 12:39
 * PS: 主启动类
 * @EnableHystrixDashboard  开启 dashboard 流监控
 * @EnableEurekaClient      标明这个 Eureka 的一个客户端，需要注册到注册中心中
 **/
@SpringBootApplication
@EnableHystrixDashboard
@EnableEurekaClient
@EnableDiscoveryClient
public class HystrixDashboardApplication {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApplication.class);
    }
}
