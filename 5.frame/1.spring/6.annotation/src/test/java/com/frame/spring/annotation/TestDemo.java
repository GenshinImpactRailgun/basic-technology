package com.frame.spring.annotation;

import com.frame.spring.annotation.pojo.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: railgun
 * 2021/6/8 11:59
 * PS: 测试类
 **/
public class TestDemo {
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDto userDto = context.getBean("userDto", UserDto.class);
        System.out.println(userDto.getName());
    }
}
