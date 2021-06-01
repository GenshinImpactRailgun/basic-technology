package com.frame.spring.hellospring;

import com.frame.spring.hellospring.pojo.Hello;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: railgun
 * 2021/6/1 21:10
 * PS:
 **/
public class TestDemo {

    public static void main(String[] args) {
        // 获取 spring 的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 我们的对象都在 spring 中管理，我们要使用，直接去里面取出来就可以
        Hello hello = (Hello) context.getBean("hello");
        System.out.println(hello.toString());
    }

    @org.junit.jupiter.api.Test
    public void test1(){

    }

}
