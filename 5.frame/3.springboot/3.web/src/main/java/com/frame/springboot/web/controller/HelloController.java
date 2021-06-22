package com.frame.springboot.web.controller;

import com.basic.comon.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: railgun
 * 2021/6/21 0:27
 * PS:
 **/
@Controller
public class HelloController {

    private final TestController testController;

    public HelloController(TestController testController) {
        this.testController = testController;
    }

    /**
     * railgun
     * 2021/6/21 1:35
     * PS: 返回数据
     **/
    @ResponseBody
    @GetMapping(value = "test")
    public String test() {
        return Constant.SLOGAN;
    }

    @GetMapping(value = "test1")
    public String test1(Model model) {
        model.addAttribute("msg", Constant.SLOGAN);
        return "test";
    }

    @ResponseBody
    @GetMapping(value = "test2/{taskId}")
    public String test2(@PathVariable(value = "taskId") String taskId) {
        testController.setTaskId(taskId);
        testController.useTaskId();
        return Constant.SLOGAN;
    }

}
