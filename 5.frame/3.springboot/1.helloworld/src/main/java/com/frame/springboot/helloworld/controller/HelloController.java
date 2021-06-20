package com.frame.springboot.helloworld.controller;

import com.basic.comon.Constant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: railgun
 * 2021/6/20 15:11
 * PS: 控制类
 **/
@RestController
public class HelloController {

    /**
     * railgun
     * 2021/6/20 15:12
     * PS: 返回数据
     **/
    @GetMapping(value = {"test", "test1"})
    public String test() {
        return Constant.SLOGAN;
    }

}
