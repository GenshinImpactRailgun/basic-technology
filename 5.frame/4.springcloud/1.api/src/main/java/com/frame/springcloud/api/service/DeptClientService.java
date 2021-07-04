package com.frame.springcloud.api.service;

import com.frame.springcloud.api.pojo.Dept;
import com.frame.springcloud.api.service.hystrix.DeptClientServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Author: railgun
 * 2021/7/4 18:31
 * PS: 接口
 * fallbackFactory   设置服务降级对应的实现类
 **/
@Component
@FeignClient(value = "2.PROVIDER-DEPT-8001", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {
    /**
     * PS: 增加一个部门
     *
     * @param dept
     * @Author: railgun
     * @return: java.lang.Boolean
     * 2021/7/1 0:30
     **/
    @PostMapping("add-dept")
    Boolean addDept(Dept dept);

    /**
     * PS: 依据主键查询部门
     *
     * @param id
     * @Author: railgun
     * @return: com.frame.springcloud.api.pojo.Dept
     * 2021/7/1 0:30
     **/
    @GetMapping("get-dept-by-id/{id}")
    Dept queryById(@PathVariable("id") Integer id);

    /**
     * PS: 查询所有部门
     *
     * @Author: railgun
     * @return: java.util.List<com.frame.springcloud.api.pojo.Dept>
     * 2021/7/1 0:31
     **/
    @GetMapping("get-all")
    List<Dept> queryAll();

    /**
     * 获取哪一个配置
     *
     * @Author: railgun
     * @return: java.lang.String
     * 2021/7/4 18:44
     **/
    @GetMapping("get-server-port")
    String getServerPort();

    /**
     * PS: 熔断器方式调用接口
     *
     * @param id
     * @Author: railgun
     * @return: com.frame.springcloud.api.pojo.Dept
     * 2021/7/4 22:46
     **/
    @GetMapping("hystrix/get-dept-by-id/{id}")
    Dept queryByIdHystrix(@PathVariable("id") Integer id);

    /**
     * PS: 熔断器获取所有的部门
     *
     * @Author: railgun
     * @return: java.util.List<com.frame.springcloud.api.pojo.Dept>
     * 2021/7/4 22:55
     **/
    @GetMapping("hystrix/get-all")
    List<Dept> getAllHystrix();

}
