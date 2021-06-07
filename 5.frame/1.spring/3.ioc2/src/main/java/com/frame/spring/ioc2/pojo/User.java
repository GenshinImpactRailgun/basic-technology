package com.frame.spring.ioc2.pojo;

/**
 * @Author: railgun
 * 2021/6/1 22:08
 * PS:
 **/
public class User {

    private String name;

    private Integer age;

    /**
     * railgun
     * 2021/6/4 16:58
     * PS: 无参构造
     **/
    public User() {
        System.out.println("执行 User 无参构造");
    }

    /**
     * railgun
     * 2021/6/4 16:59
     * PS: 有参构造
     **/
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
        System.out.println("有参构造执行 name：" + name + "，age：" + age);
    }

    public void show() {
        System.out.println("name=" + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
