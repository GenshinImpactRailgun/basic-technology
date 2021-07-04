package com.frame.springcloud.consumerdept.controller;

import com.frame.springcloud.api.pojo.Dept;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author: railgun
 * 2021/7/4 13:04
 * PS: 控制层
 * 消费者不应该有 server 层
 **/
@RestController
public class DemoController {

    private static final String REST_URL_PREFEX = "http://127.0.0.1:8001/";

    /**
     * railgun
     * 2021/7/4 13:16
     * PS: 用于发送 http 请求的模板
     **/
    private final RestTemplate restTemplate;

    public DemoController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * railgun
     * 2021/7/4 13:16
     * PS: 依据主键获取部门
     **/
    @GetMapping("get-dept-by-id/{id}")
    public Dept getDeptById(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(REST_URL_PREFEX + "get-dept-by-id/" + id, Dept.class);
    }

    /**
     * railgun
     * 2021/7/4 13:17
     * PS: 获取所有部门
     **/
    @GetMapping("get-all")
    public List<Dept> getAll() {
        return restTemplate.getForObject(REST_URL_PREFEX + "get-all", List.class);
    }

    /**
     * railgun
     * 2021/7/4 13:20
     * PS: 添加一个部门对象
     **/
    @PostMapping("add-dept")
    public Boolean addDept(@RequestBody Dept dept) {
        return restTemplate.postForObject(REST_URL_PREFEX + "add-dept", dept, Boolean.class);
    }

}
