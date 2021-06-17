package com.frame.springmvc.annotation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: railgun
 * 2021/6/16 22:20
 * PS:
 **/
@Controller
public class RestFulController {

    /**
     * railgun
     * 2021/6/16 23:04
     * PS: 普通方式请求
     **/
    @RequestMapping("test")
    public String test(int a, int b, Model model) {
        model.addAttribute("msg", a + b);
        return "hello";
    }

    /**
     * railgun
     * 2021/6/16 23:04
     * PS: RestFul 风格
     **/
    @RequestMapping("test2/{a}/{b}")
    public String test2(@PathVariable int a, @PathVariable int b, Model model) {
        model.addAttribute("msg", a + b);
        return "hello";
    }

    /**
     * railgun
     * 2021/6/16 23:04
     * PS: 指定请求方式
     **/
    @RequestMapping(value = "test3/{a}/{b}", method = RequestMethod.POST)
    public String test3(@PathVariable int a, @PathVariable int b, Model model) {
        model.addAttribute("msg", a + b);
        return "hello";
    }

}
