package com.frame.spring.ioc;

import com.frame.spring.ioc.dao.UserDaoMysqlImpl;
import com.frame.spring.ioc.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: railgun
 * 2021/5/31 23:56
 * PS:
 **/
public class TestDemo {

    /**
     * railgun
     * 2021/6/1 0:06
     * PS: 相当于控制层的代码
     **/
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.setUserDaoIoc(new UserDaoMysqlImpl());
        userService.getUser();
    }

    @Test
    public void test1() {
        // 拿到 spring 的容器
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        // 容器获取好之后，直接 get 就行了
        UserServiceImpl userService = (UserServiceImpl) context.getBean("userService");
        userService.getUser();
    }

}
