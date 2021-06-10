package com.frame.spring.aop.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * @Author: railgun
 * 2021/6/10 20:53
 * PS: 使用注解实现 AOP
 **/
@Aspect
public class AnnotationPointCut {

    @Before("execution(* com.frame.spring.aop.service.BaseServiceImpl.*(..))")
    public void before() {
        System.out.println("2、@Before");
    }

    @After("execution(* com.frame.spring.aop.service.BaseServiceImpl.*(..))")
    public void after() {
        System.out.println("4、@After");
    }

    @AfterReturning("execution(* com.frame.spring.aop.service.BaseServiceImpl.*(..))")
    public void afterReturning() {
        System.out.println("3、@AfterReturning");
    }

    @Around("execution(* com.frame.spring.aop.service.BaseServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("1、环绕前");
        // 执行方法
        Object proceed = joinPoint.proceed();
        System.out.println("5、环绕后");
        return proceed;
    }

}
