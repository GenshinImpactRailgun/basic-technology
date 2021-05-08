package com.java.annotation;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AnnotationDemo {

    @Test
    public void test1() {
        annotationRead();
    }

    /**
     * railgun
     * 2021/5/8 22:45
     * PS: 读取注解
     **/
    @SneakyThrows
    public void annotationRead() {
        // 获取类上的注解
        Class<AnnotationUse> clazz = AnnotationUse.class;
        Railgun railgun = clazz.getAnnotation(Railgun.class);
        System.out.println(railgun.value());

        // 获取成员变量上的注解
        Field name = clazz.getField("name");
        Railgun annotationOnField = name.getAnnotation(Railgun.class);
        System.out.println(annotationOnField.value());

        // 获取hello方法上的注解
        Method hello = clazz.getMethod("sayHello", (Class<?>[]) null);
        Railgun annotationOnMethod = hello.getAnnotation(Railgun.class);
        System.out.println(annotationOnMethod.value());

        // 获取defaultMethod方法上的注解
        Method defaultMethod = clazz.getMethod("defaultMethod", (Class<?>[]) null);
        Railgun annotationOnDefaultMethod = defaultMethod.getAnnotation(Railgun.class);
        System.out.println(annotationOnDefaultMethod.value());
    }

}
