package com.frame.spring.transaction;

import com.basic.comon.util.GsonUtil;
import com.frame.spring.transaction.dao.UserTestMapper;
import com.frame.spring.transaction.pojo.UserTest;
import com.frame.spring.transaction.service.UserTestService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @Author: railgun
 * 2021/6/11 18:47
 * PS:
 **/
public class TestDemo {
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserTestMapper userTestMapper = context.getBean("userTestMapper", UserTestMapper.class);
        List<UserTest> list = userTestMapper.selectUserTestAll();
        list.forEach(GsonUtil::objectSoutJson);
    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserTestService userTestService = context.getBean("userTestService", UserTestService.class);
        userTestService.insertAndDelete(new UserTest(19, "railgun-test", "测试的一个用户", "OtakuTechnologySaveTheWorld"));
    }
}
