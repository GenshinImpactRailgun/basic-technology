package com.frame.springboot.data.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: railgun
 * 2021/6/24 0:27
 * PS:
 **/
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * railgun
     * 2021/6/24 0:34
     * PS: 后台监控，web.xml
     * 因为 SpringBoot 内置了 servlet 容器，没有了 web.xml，替代方法
     **/
    @Bean
    public ServletRegistrationBean<StatViewServlet> servletRegistrationBean() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParameters = new HashMap<>(8);
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");
        initParameters.put("allow", "");
        bean.setInitParameters(initParameters);
        return bean;
    }

    /**
     * railgun
     * 2021/6/24 0:47
     * PS: 注册一个过滤器
     * TODO 研究 SpringBoot 如何自定义设置自己的过滤器
     **/
    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParameters = new HashMap<>(8);
        initParameters.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParameters);
        return bean;
    }

}
