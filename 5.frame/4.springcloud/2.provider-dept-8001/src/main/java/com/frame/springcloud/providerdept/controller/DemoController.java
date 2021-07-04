package com.frame.springcloud.providerdept.controller;

import com.frame.springcloud.api.pojo.Dept;
import com.frame.springcloud.providerdept.service.DeptService;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: railgun
 * 2021/7/1 0:52
 * PS: 控制层
 **/
@Slf4j
@RestController
public class DemoController {

    private final DeptService deptService;

    private final DiscoveryClient discoveryClient;

    public DemoController(DeptService deptService, DiscoveryClient discoveryClient) {
        this.deptService = deptService;
        this.discoveryClient = discoveryClient;
    }


    /**
     * railgun
     * 2021/7/1 0:53
     * PS: 获取所有的部门
     **/
    @GetMapping("get-all")
    public List<Dept> getAll() {
        log.info("【getAll】【开始调用】");
        return deptService.queryAll();
    }

    @PostMapping("add-dept")
    public Boolean addDept(@RequestBody Dept dept) {
        return deptService.addDept(dept);
    }

    @GetMapping("get-dept-by-id/{id}")
    public Dept getDeptById(@PathVariable("id") Integer id) {
        return deptService.queryById(id);
    }

    /**
     * railgun
     * 2021/7/4 14:47
     * PS: 获取注册服务的信息
     **/
    @GetMapping("discovery")
    public Map<String, Object> getDiscoveryMessage() {
        List<String> services = discoveryClient.getServices();
        List<ServiceInstance> instances = discoveryClient.getInstances("2.PROVIDER-DEPT-8001");
        Map<String, Object> map = Maps.newHashMapWithExpectedSize(2);
        map.put("services", services);
        map.put("instances", instances);
        return map;
    }

    @Value(value = "${whichRibbon:}")
    private String whichRibbon;

    /**
     * railgun
     * 2021/7/4 16:29
     * PS:
     **/
    @GetMapping("get-which-ribbon")
    public String getWhichRibbon(){
        return whichRibbon;
    }

}
