package com.frame.springmvc.annotation.controller;

import com.basic.comon.util.GsonUtil;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: railgun
 * 2021/6/17 20:01
 * PS:
 **/
@Controller
@RequestMapping(value = "view")
public class ViewController {

    /**
     * railgun
     * 2021/6/17 20:07
     * PS: 无视图解析器的返回
     **/
    @GetMapping(value = "test1")
    public String test1(Model model) {
        model.addAttribute("msg", "railgun");
        return "/WEB-INF/jsp/hello.jsp";
    }

    /**
     * railgun
     * 2021/6/17 20:07
     * PS: 转发
     **/
    @GetMapping(value = "/test2")
    public String test2() {
        return "forward:/index.jsp";
    }

    /**
     * railgun
     * 2021/6/17 20:07
     * PS: 重定向
     **/
    @GetMapping(value = "/test3")
    public String test3() {
        return "redirect:/index.jsp";
    }

    @GetMapping(value = "/test4")
    public String test4(@RequestParam(value = "username", required = false) String name, Model model) {
        model.addAttribute("msg", name);
        return "hello";
    }

    @GetMapping(value = "/test5")
    public String test5(UserVo userVo, Model model) {
        System.out.println(userVo);
        return "hello";
    }

    @GetMapping(value = "/test6")
    public String test6(@RequestParam(value = "username", required = false) String name, Model model) {
        model.addAttribute("msg", name);
        return "hello";
    }

    @PostMapping(value = "/test7")
    public String test7(@RequestParam(value = "username", required = false) String name, Model model) {
        model.addAttribute("msg", name);
        return "hello";
    }

    /**
     * railgun
     * 2021/6/17 21:10
     * PS: 后台 JSON 处理
     **/
    @GetMapping(value = "/test8")
    public String test8(Model model) {
        UserVo userVo = new UserVo();
        userVo.setId(38);
        userVo.setName("超电磁炮");
        userVo.setBirthDay(new Date());
        model.addAttribute("msg", GsonUtil.objectToJson(userVo));
        return "hello";
    }

    /**
     * railgun
     * 2021/6/17 22:06
     * PS: 对外提供返回 json 数据的接口
     **/
    @ResponseBody
    @GetMapping(value = "/test9", produces = "application/json;charset=utf-8")
    public String test9() {
        UserVo userVo = new UserVo();
        userVo.setId(38);
        userVo.setName("超电磁炮");
        userVo.setBirthDay(new Date());
        return GsonUtil.objectToJson(userVo);
    }

    /**
     * railgun
     * 2021/6/17 22:49
     * PS: 向前台返回 map 的时候，不会发生中文乱码的问题
     **/
    @ResponseBody
    @GetMapping(value = "/test10")
    public Map<String, UserVo> test10() {
        UserVo userVo = new UserVo();
        userVo.setId(38);
        userVo.setName("超电磁炮");
        userVo.setBirthDay(new Date());
        Map<String, UserVo> map = new HashMap<>(4);
        map.put("user", userVo);
        return map;
    }

    @Data
    private static class UserVo {
        private int id;
        private String name;
        private Date birthDay;
    }


}
