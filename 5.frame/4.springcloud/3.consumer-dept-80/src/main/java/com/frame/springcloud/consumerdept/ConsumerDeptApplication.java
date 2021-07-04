package com.frame.springcloud.consumerdept;

import com.frame.springcloud.config.CustomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: railgun
 * 2021/7/4 13:03
 * PS: 主启动类
 * ribbon Eureka 整合以后，不用关心 ip 端口号
 * EnableFeignClients   指定扫描哪些包下的 @FeignClient 注解
 * <p>
 * ------ feign 使用 -------
 * @ComponentScans({@ComponentScan("com.frame.springcloud.api")}) 该方式会兼容本工程下的 bean 的注入
 * @ComponentScan("com.frame.springcloud.api") 该方式会导致本工程下的 bean 的注入失效
 * PS：不需要 @ComponentScans 注解，并不需要扫描 com.frame.springcloud.api 改路径下的文件
 * DeptClientService 的注入已经由 @EnableFeignClients 注解完成操作了
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@RibbonClient(name = "2.PROVIDER-DEPT-8001", configuration = CustomRule.class)
@EnableFeignClients(basePackages = {"com.frame.springcloud"})
//@ComponentScans({@ComponentScan("com.frame.springcloud.api")})
//@ComponentScan("com.frame.springcloud.api")
public class ConsumerDeptApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerDeptApplication.class);
    }
}
