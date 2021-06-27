package com.frame.springboot.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Author: railgun
 * 2021/6/27 11:49
 * PS: swagger 配置类
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * railgun
     * 2021/6/27 11:54
     * PS: 配置 docket 以及 Swagger 具体参数以及配置
     **/
    @Bean
    public Docket setDocketGet(Environment environment) {
        Profiles profiles = Profiles.of("dev");
        boolean whetherEnableSwagger = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("get")
                // 禁用浏览器访问 swagger 【根据部署环境来判断，只有 dev 环境显示】
                .enable(whetherEnableSwagger)
                // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .select()
                /**
                 * basePackage              扫描包路径
                 * any                      扫描全部
                 * none                     不扫描
                 * withClassAnnotation      扫描类上的注解，参数是一个注解的反射对象
                 * withMethodAnnotation     扫描方法上的注解
                 **/
                .apis(RequestHandlerSelectors.basePackage("com.frame.springboot.swagger.controller"))
                //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(GetMapping.class))
                // 配置接口扫描过滤【只展示 /test、/test/** 路径下的接口】
                /**
                 * any() // 任何请求都扫描
                 * none() // 任何请求都不扫描
                 * regex(final String pathRegex) // 通过正则表达式控制
                 * ant(final String antPattern) // 通过ant()控制
                 **/
                .paths(PathSelectors.ant("/test/**"))
                .build();
    }

    @Bean
    public Docket setDocketPost(Environment environment) {
        Profiles profiles = Profiles.of("dev");
        boolean whetherEnableSwagger = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.groupName("post")
                // 禁用浏览器访问 swagger 【根据部署环境来判断，只有 dev 环境显示】
                .enable(whetherEnableSwagger)
                // 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .select()
                /**
                 * basePackage              扫描包路径
                 * any                      扫描全部
                 * none                     不扫描
                 * withClassAnnotation      扫描类上的注解，参数是一个注解的反射对象
                 * withMethodAnnotation     扫描方法上的注解
                 **/
                //.apis(RequestHandlerSelectors.basePackage("com.frame.springboot.swagger.controller"))
                //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(PostMapping.class))
                // 配置接口扫描过滤【只展示 /test、/test/** 路径下的接口】
                /**
                 * any() // 任何请求都扫描
                 * none() // 任何请求都不扫描
                 * regex(final String pathRegex) // 通过正则表达式控制
                 * ant(final String antPattern) // 通过ant()控制
                 **/
                .paths(PathSelectors.ant("/set-user**"))
                .build();
    }

    /**
     * railgun
     * 2021/6/27 11:59
     * PS: 配置文档信息
     **/
    private ApiInfo apiInfo() {
        Contact contact = new Contact("railgun", "http://railgun.com", "genshinimpactrailgun@gmail.com");
        return new ApiInfo(
                // 标题
                "Swagger学习",
                // 描述
                "学习演示如何配置Swagger",
                // 版本
                "v1.0",
                // 组织链接
                "http://terms.service.url/组织链接",
                // 联系人信息
                contact,
                // 许可
                "Apach 2.0 许可",
                // 许可连接
                "许可链接",
                // 扩展
                new ArrayList<>()
        );
    }

}
