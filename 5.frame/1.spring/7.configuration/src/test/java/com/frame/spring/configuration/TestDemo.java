package com.frame.spring.configuration;

import com.frame.spring.configuration.core.BeanConfig;
import com.frame.spring.configuration.pojo.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author: railgun
 * 2021/6/8 12:30
 * PS: 测试方法，使用 AnnotationConfigApplicationContext 获取陪托管的对象
 **/
public class TestDemo {
    @Test
    public void test1() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        UserDto userDto = context.getBean("userDto", UserDto.class);
        System.out.println(userDto.getName());
    }
}
