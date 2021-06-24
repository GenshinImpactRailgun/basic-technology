package com.frame.springboot.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author: railgun
 * 2021/6/24 20:29
 * PS:
 **/
@Controller
public class SecurityController {

    @GetMapping({"", "index"})
    public String index() {
        return "index";
    }

    @GetMapping("login-in")
    public String loginIn() {
        return "login";
    }

    @GetMapping("go-to-html/{level}/{order}")
    public String goToHtml(@PathVariable("level") int level, @PathVariable("order") int order) {
        return "level" + level + "/" + order;
    }

}
