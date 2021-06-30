package com.frame.springcloud.providerdept.controller;

import com.frame.springcloud.api.pojo.Dept;
import com.frame.springcloud.providerdept.service.DeptService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: railgun
 * 2021/7/1 0:52
 * PS: 控制层
 **/
@RestController
public class DemoController {

    private final DeptService deptService;

    public DemoController(DeptService deptService) {
        this.deptService = deptService;
    }


    /**
     * railgun
     * 2021/7/1 0:53
     * PS: 获取所有的部门
     **/
    @GetMapping("get-all")
    public List<Dept> getAll() {
        return deptService.queryAll();
    }

    @PostMapping("add-dept")
    public Boolean addDept(@RequestBody Dept dept) {
        return deptService.addDept(dept);
    }

    @GetMapping("/get-dept-by-id/{id}")
    public Dept getDeptById(@PathVariable("id") Integer id) {
        return deptService.queryById(id);
    }

}
