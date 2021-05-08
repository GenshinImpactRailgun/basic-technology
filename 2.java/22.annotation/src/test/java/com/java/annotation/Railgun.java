package com.java.annotation;

import java.lang.annotation.*;

/**
 * @Author: railgun
 * 2021/5/8 21:28
 * PS: 定义注解
 * @Documented 用于制作文档，不是很重要，忽略便是
 * @Target 限定该注解的使用位置。不写默认各个位置都可以【ElementType.TYPE、ElementType.METHOD、ElementType.FIELD 等等】【class、method、field】
 * @Retention 保留策略【RetentionPolicy.SOURCE、RetentionPolicy.CLASS、RetentionPolicy.RUNTIME】【java 文件、class 文件、jvm 运行时】
 **/
@Documented
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Railgun {

    /**
     * railgun
     * 2021/5/8 21:32
     * PS: "no description" 这个是默认值
     **/
    String value() default "no description";

}
