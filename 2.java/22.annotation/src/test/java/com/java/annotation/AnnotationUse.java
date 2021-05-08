package com.java.annotation;

/**
 * @Author: railgun
 * 2021/5/8 23:14
 * PS: 使用注解
 **/
@Railgun(value = "class")
public class AnnotationUse {

    @Railgun(value = "field")
    public String name = "name";

    @Railgun(value = "method")
    public String sayHello() {
        return "hello";
    }

    @Railgun()
    public String defaultMethod() {
        return "default";
    }

}
