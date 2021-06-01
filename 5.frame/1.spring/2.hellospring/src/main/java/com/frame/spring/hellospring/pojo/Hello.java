package com.frame.spring.hellospring.pojo;

/**
 * @Author: railgun
 * 2021/6/1 21:15
 * PS:
 **/
public class Hello {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void show() {
        System.out.println("Hello：" + name);
    }

    @Override
    public String toString() {
        return "Hello{" +
                "name='" + name + '\'' +
                '}';
    }
}
