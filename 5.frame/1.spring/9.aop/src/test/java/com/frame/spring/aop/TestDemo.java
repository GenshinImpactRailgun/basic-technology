package com.frame.spring.aop;

import com.frame.spring.aop.dao.entity.Mosquito;
import com.frame.spring.aop.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: railgun
 * 2021/6/10 0:15
 * PS:
 **/
public class TestDemo {
    /**
     * railgun
     * 2021/6/10 20:52
     * PS:
     **/
    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BaseService<Mosquito> baseService = context.getBean("baseService", BaseService.class);
        baseService.delete();
    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");
        BaseService<Mosquito> baseService = context.getBean("baseService", BaseService.class);
        baseService.delete();
    }

    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext3.xml");

        System.out.println("----------------------------------- 分割线 -----------------------------------");
        BaseService baseService = context.getBean("baseService", BaseService.class);
        baseService.insert();

        /*System.out.println("----------------------------------- 分割线 -----------------------------------");
        LogServiceImpl logServiceImpl = context.getBean("logService", LogServiceImpl.class);
        logServiceImpl.recordLog();

        System.out.println("----------------------------------- 分割线 -----------------------------------");
        MosquitoServiceImpl mosquitoService = context.getBean("mosquitoService", MosquitoServiceImpl.class);
        mosquitoService.delete();

        System.out.println("----------------------------------- 分割线 -----------------------------------");
        TableServiceImpl tableService = context.getBean("tableService", TableServiceImpl.class);
        tableService.update();

        System.out.println("----------------------------------- 分割线 -----------------------------------");
        DiyServiceImpl diyServiceImpl = context.getBean("diyService", DiyServiceImpl.class);
        diyServiceImpl.before();*/
    }
}
