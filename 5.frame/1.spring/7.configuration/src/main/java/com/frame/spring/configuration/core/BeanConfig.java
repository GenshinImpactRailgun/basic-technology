package com.frame.spring.configuration.core;

import com.frame.spring.configuration.pojo.UserDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author: railgun
 * 2021/6/8 12:32
 * PS: 配置类
 **/
@Configuration
@ComponentScan(value = {"com.frame.spring.configuration"})
public class BeanConfig {

    @Bean
    public UserDto getUserDto() {
        return new UserDto();
    }

}
