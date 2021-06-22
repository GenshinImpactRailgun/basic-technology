package com.frame.springboot.web.controller;

import org.springframework.stereotype.Controller;

/**
 * @Author: railgun
 * 2021/6/21 10:10
 * PS:
 **/
@Controller
public class TestController {

    String taskId;

    public void setTaskId(String taskId){
        this.taskId = taskId;
    }

    public void useTaskId(){
        System.out.println(taskId);
    }

}
