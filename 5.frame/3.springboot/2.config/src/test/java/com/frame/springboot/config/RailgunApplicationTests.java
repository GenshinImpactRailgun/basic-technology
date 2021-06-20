package com.frame.springboot.config;

import com.frame.springboot.config.pojo.Dog;
import com.frame.springboot.config.pojo.Person;
import com.frame.springboot.config.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: railgun
 * 2021/6/20 14:54
 * PS: 启动类
 **/
@SpringBootTest
public class RailgunApplicationTests {

    @Autowired
    private Dog dog;
    @Autowired
    private Person person;
    @Autowired
    private User user;

    @Test
    void contextLoads() {
        System.out.println(dog);
        System.out.println(person);
        System.out.println(user);
    }
}
