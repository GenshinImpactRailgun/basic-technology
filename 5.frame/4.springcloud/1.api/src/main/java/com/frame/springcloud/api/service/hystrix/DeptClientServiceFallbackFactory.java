package com.frame.springcloud.api.service.hystrix;

import com.frame.springcloud.api.pojo.Dept;
import com.frame.springcloud.api.service.DeptClientService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: railgun
 * 2021/7/4 23:07
 * PS: 服务降级工厂类
 **/
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {
    @Override
    public Object create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Boolean addDept(Dept dept) {
                return null;
            }

            @Override
            public Dept queryById(Integer id) {
                return new Dept()
                        .setId(id)
                        .setDeptName("【getDeptById】【不存在该用户：" + id + "】")
                        .setDbSource("空，没有满足条件的数据库");
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }

            @Override
            public String getServerPort() {
                return null;
            }

            @Override
            public Dept queryByIdHystrix(Integer id) {
                return null;
            }

            @Override
            public List<Dept> getAllHystrix() {
                return null;
            }
        };
    }
}
