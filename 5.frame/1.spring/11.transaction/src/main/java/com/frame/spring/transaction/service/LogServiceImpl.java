package com.frame.spring.transaction.service;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author: railgun
 * 2021/6/10 0:21
 * PS:
 **/
public class LogServiceImpl implements MethodBeforeAdvice, AfterReturningAdvice {

    /**
     * PS: 方法执行前调用的方法
     *
     * @param method  要执行的目标对象的方法
     * @param objects 参数
     * @param o       目标对象
     * @Author: railgun
     * @return: void
     * 2021/6/10 0:23
     **/
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        assert o != null;
        System.out.println("类名：" + o.getClass().getName() + "方法名：" + method.getName());
    }

    /**
     * PS: 方法执行后调用的方法
     *
     * @param returnValue 返回值
     * @param method
     * @param args
     * @param target
     * @Author: railgun
     * @return: void
     * 2021/6/10 0:26
     **/
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        assert target != null;
        System.out.println("类名：" + target.getClass().getName() + "方法名：" + method.getName() + "，返回结果为：" + returnValue);
    }

    public void recordLog() {
        System.out.println("日志服务执行记录日志服务；");
    }

}
