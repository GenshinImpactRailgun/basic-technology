package com.frame.springboot.task.controller;

import com.basic.comon.Constant;
import com.frame.springboot.task.service.AsyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: railgun
 * 2021/6/27 21:44
 * PS: 控制层
 **/
@RestController
public class DemoController {

    private final AsyncService asyncService;

    public DemoController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("test")
    public String test() {
        asyncService.hello();
        return Constant.SLOGAN;
    }
}
