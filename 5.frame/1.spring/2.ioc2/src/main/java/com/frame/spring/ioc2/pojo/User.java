package com.frame.spring.ioc2.pojo;

/**
 * @Author: railgun
 * 2021/6/1 22:08
 * PS:
 **/
public class User {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("name=" + name);
    }

    public User() {
        System.out.println("执行 User 无参构造");
    }

}
