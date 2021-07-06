package com.frame.springcloud.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author: railgun
 * 2021/7/6 22:52
 * PS: 配置中心启动类
 * 需要配合 gitee 配置仓库配置
 * 仓库地址：https://gitee.com/ai39Me/spring-cloud-config.git
 **/
@SpringBootApplication
@EnableConfigServer
public class ConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class);
    }
}
