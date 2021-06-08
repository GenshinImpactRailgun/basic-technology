package com.frame.spring.autowired.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;

import javax.annotation.Resource;

/**
 * @Author: railgun
 * 2021/6/8 9:14
 * PS: 人
 **/
public class People {

    @Nullable
    private String name;
    @Autowired
    @Qualifier(value = "cat")
    private Cat cat;
    @Autowired
    private Dog dog;
    /**
     * 2021/6/8 10:07 @railgun required = false 标识该属性可以不用在容器中存在，如果是默认的 true且 容器中不存在该类型对象，则会抛出异常
     **/
    @Autowired(required = false)
    private Chicken chicken;

    @Resource(name = "cat")
    private Cat cat111;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "People{" +
                "cat=" + cat +
                ", dog=" + dog +
                '}';
    }

    public People() {
    }

    public People(Cat cat, Dog dog) {
        this.cat = cat;
        this.dog = dog;
    }

}
