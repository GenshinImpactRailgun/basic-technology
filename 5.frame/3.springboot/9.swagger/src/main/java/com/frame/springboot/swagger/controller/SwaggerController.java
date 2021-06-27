package com.frame.springboot.swagger.controller;

import com.frame.springboot.swagger.pojo.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: railgun
 * 2021/6/27 11:50
 * PS: 控制层
 **/
@Api(tags = "swagger 模块")
@RestController
public class SwaggerController {

    @GetMapping("test")
    public String testGet(@RequestParam(value = "name") String name) {
        return name;
    }

    @PostMapping("test/{name}")
    public String testPost(@PathVariable(value = "name") String name) {
        return name;
    }

    @ApiOperation(value = "赋予 UserDto 值")
    @PostMapping("set-user")
    public UserDto getUserDto(@ApiParam(value = "接收对象") @RequestBody UserDto userDto) {
        return userDto;
    }

}
