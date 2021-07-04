package com.frame.springcloud.consumerdept.controller;

import com.frame.springcloud.api.pojo.Dept;
import com.frame.springcloud.api.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: railgun
 * 2021/7/4 13:04
 * PS: 控制层
 * 消费者不应该有 server 层
 **/
@RestController
@RequestMapping("hystrix")
public class HystrixController {

    @Autowired(required = false)
    private DeptClientService deptClientService;

    /**
     * railgun
     * 2021/7/4 13:16
     * PS: 依据主键获取部门
     **/
    @GetMapping("get-dept-by-id/{id}")
    public Dept getDeptById(@PathVariable("id") Integer id) {
        return deptClientService.queryByIdHystrix(id);
    }

    /**
     * railgun
     * 2021/7/4 22:56
     * PS: 获取所有的部门信息
     **/
    @GetMapping("get-all")
    public List<Dept> getAllHystrix() {
        return deptClientService.getAllHystrix();
    }

}
