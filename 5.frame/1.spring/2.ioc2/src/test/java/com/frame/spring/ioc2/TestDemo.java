package com.frame.spring.ioc2;

import com.frame.spring.ioc2.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: railgun
 * 2021/6/1 22:07
 * PS:
 **/
public class TestDemo {

    @Test
    public void test1() {
        User user = new User();
    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        User user = (User) context.getBean("user");
        user.show();
    }

}
