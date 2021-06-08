package com.frame.spring.autowired;

import com.frame.spring.autowired.pojo.People;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: railgun
 * 2021/6/8 9:28
 * PS: 测试类
 **/
public class TestDemo {
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        People people = context.getBean("people", People.class);
        people.getCat().barked();
        people.getDog().barked();

        People people3 = context.getBean("people3", People.class);
        people3.getCat().barked();
        people3.getDog().barked();

        People people5 = context.getBean("people5", People.class);
        people5.getCat().barked();
        people5.getDog().barked();
    }
}
