package com.frame.springcloud.providerdept.controller;

import com.frame.springcloud.api.pojo.Dept;
import com.frame.springcloud.providerdept.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: railgun
 * 2021/7/1 0:52
 * PS: 熔断器
 **/
@Slf4j
@RestController
@RequestMapping("hystrix")
public class HystrixController {

    private final DeptService deptService;

    public HystrixController(DeptService deptService) {
        this.deptService = deptService;
    }

    /**
     * railgun
     * 2021/7/1 0:53
     * PS: 获取所有的部门
     **/
    @GetMapping("get-all")
    public List<Dept> getAll() {
        log.info("【getAll】【开始调用】");
        List<Dept> deptList = deptService.queryAll();
        if (!CollectionUtils.isEmpty(deptList)) {
            throw new RuntimeException("【getAll】【获取所有部门信息异常】");
        }
        return deptList;
    }

    /**
     * railgun
     * 2021/7/4 22:37
     * PS: 添加熔断支持
     **/
    @GetMapping("get-dept-by-id/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept getDeptById(@PathVariable("id") Integer id) {
        Dept dept = deptService.queryById(id);
        if (null == dept) {
            throw new RuntimeException("【getDeptById】【不存在该用户：" + id + "】");
        }
        return dept;
    }

    /**
     * railgun
     * 2021/7/4 22:38
     * PS: 服务发生熔断之后调用的方法
     **/
    private Dept hystrixGet(Integer id) {
        return new Dept()
                .setId(id)
                .setDeptName("【getDeptById】【不存在该用户：" + id + "】")
                .setDbSource("空，没有满足条件的数据库");
    }

}
