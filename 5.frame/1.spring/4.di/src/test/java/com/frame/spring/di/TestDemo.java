package com.frame.spring.di;

import com.frame.spring.di.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: railgun
 * 2021/6/7 12:25
 * PS: 测试类
 **/
public class TestDemo {

    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student student = (Student) context.getBean("student");
        Student student2 = (Student) context.getBean("student2");
        Student student3 = (Student) context.getBean("student3");
        System.out.println(student);
        System.out.println(student2);
        System.out.println(student3);

        Student student51 = (Student) context.getBean("student5");
        Student student52 = (Student) context.getBean("student5");
        System.out.println(student51.hashCode());
        System.out.println(student52.hashCode());
    }

}
