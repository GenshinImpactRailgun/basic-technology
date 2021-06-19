package com.frame.springmvc.interceptor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: railgun
 * 2021/6/19 23:14
 * PS:
 **/
@Controller
public class HelloController {

    @ResponseBody
    @GetMapping(value = "test")
    public String test() {
        System.out.println("-- HelloController.test 方法执行 --");
        return "OtakuTechnologySaveTheWorld";
    }

    @ResponseBody
    @GetMapping(value = "test-login-1")
    public String test2() {
        System.out.println("-- HelloController.test 方法执行 --");
        return "OtakuTechnologySaveTheWorld";
    }

}
