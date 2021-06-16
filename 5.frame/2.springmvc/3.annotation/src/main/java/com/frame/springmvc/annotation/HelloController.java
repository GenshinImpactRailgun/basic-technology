package com.frame.springmvc.annotation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: railgun
 * 2021/6/16 21:42
 * PS:
 **/
@Controller
public class HelloController {

    @RequestMapping(value = "/hello")
    public String hello(Model model) {
        model.addAttribute("msg", "Hello, SpringMVC Annotation");
        return "hello";
    }

}
