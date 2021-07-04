package com.frame.springcloud.consumerdept.controller;

import com.frame.springcloud.api.pojo.Dept;
import com.frame.springcloud.api.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("feign")
public class FeignController {

    /**
     * 2021/7/4 15:47 @railgun 由 ribbon 实现负载均衡，不能定死访问地址
     * 应该通过服务名访问
     **/
    private static final String REST_URL_PREFEX_IP = "http://127.0.0.1:8001/";
    private static final String REST_URL_PREFEX = "http://2.PROVIDER-DEPT-8001/";

    /**
     * railgun
     * 2021/7/4 13:16
     * PS: 用于发送 http 请求的模板
     **/
    private final RestTemplate restTemplate;

    @Autowired(required = false)
    private DeptClientService deptClientService;

    public FeignController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * railgun
     * 2021/7/4 13:16
     * PS: 依据主键获取部门
     **/
    @GetMapping("get-dept-by-id/{id}")
    public Dept getDeptById(@PathVariable("id") Integer id) {
        return deptClientService.queryById(id);
    }

    /**
     * railgun
     * 2021/7/4 13:17
     * PS: 获取所有部门
     **/
    @GetMapping("get-all")
    public List<Dept> getAll() {
        return deptClientService.queryAll();
    }

    /**
     * railgun
     * 2021/7/4 13:20
     * PS: 添加一个部门对象
     **/
    @PostMapping("add-dept")
    public Boolean addDept(@RequestBody Dept dept) {
        return deptClientService.addDept(dept);
    }

    /**
     * railgun
     * 2021/7/4 16:37
     * PS: 测试负载均衡接口
     **/
    @GetMapping("get-server-port")
    public String getServerPort() {
        return deptClientService.getServerPort();
    }

}
