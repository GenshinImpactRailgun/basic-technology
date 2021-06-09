package com.frame.spring.aop;

import com.frame.spring.aop.dao.entity.Mosquito;
import com.frame.spring.aop.service.BaseService;
import com.frame.spring.aop.service.BaseServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: railgun
 * 2021/6/10 0:15
 * PS:
 **/
public class TestDemo {
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
        BaseService<Mosquito> baseService = context.getBean("baseService", BaseService.class);
        baseService.delete();
    }
}
