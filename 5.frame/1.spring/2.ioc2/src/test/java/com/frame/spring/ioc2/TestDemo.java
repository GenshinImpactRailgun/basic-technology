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
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 等于号前面是变量名，后面是对象地址
        User user = (User) context.getBean("user");
        User user2 = (User) context.getBean("userdkfjidji");
        User u1 = (User) context.getBean("u1");
        User u2 = (User) context.getBean("u2");
        // 两次获取到的对象的地址是一致的
        System.out.println(user == user2);
        System.out.println(u1 == u2);
        System.out.println(user == u1);
    }

}
