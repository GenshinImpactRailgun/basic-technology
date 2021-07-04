package com.frame.springcloud.consumerdept;

import com.frame.springcloud.config.CustomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @Author: railgun
 * 2021/7/4 13:03
 * PS: 主启动类
 * ribbon Eureka 整合以后，不用关心 ip 端口号
 **/
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "2.PROVIDER-DEPT-8001", configuration = CustomRule.class)
public class ConsumerDeptApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerDeptApplication.class);
    }
}
