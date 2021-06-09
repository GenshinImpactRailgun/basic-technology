package com.frame.spring.proxy;

import com.frame.spring.proxy.demo3.BaseService;
import com.frame.spring.proxy.demo3.MosquitoServiceImpl;
import com.frame.spring.proxy.demo3.entity.Mosquito;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * @Author: railgun
 * 2021/6/9 19:37
 * PS:
 **/
public class TestDemo {

    /**
     * railgun
     * 2021/6/9 23:27
     * PS: 朴素的写法
     **/
    @Test
    public void test1() {
        System.out.println("--------------- 朴素的写法 ---------------");
        // 将要被代理的对象
        MosquitoServiceImpl mosquitoService = new MosquitoServiceImpl();
        // 代理对象执行方法的地方
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object invoke = method.invoke(mosquitoService, args);
                recordMethodMessage(method);
                return invoke;
            }

            private void recordMethodMessage(Method method) {
                System.out.println(method.getName() + "方法被代理执行，执行时间：" + new Date());
            }
        };
        // 生成代理对象
        BaseService<Mosquito> proxyInstance = (BaseService<Mosquito>) Proxy.newProxyInstance(mosquitoService.getClass().getClassLoader(), new Class<?>[]{BaseService.class}, invocationHandler);
        // 执行方法
        proxyInstance.insert();
    }

    /**
     * railgun
     * 2021/6/9 23:34
     * PS: 优雅的写法
     **/
    @Test
    public void test2() {
        System.out.println("--------------- 优雅的写法 ---------------");
        ProxyFactory<BaseService<Mosquito>> proxyFactory = new ProxyFactory<>(new MosquitoServiceImpl());
        BaseService<Mosquito> service = proxyFactory.getProxyInstance();
        service.insert();
    }

    /**
     * @Author: railgun
     * 2021/6/9 23:34
     * PS:
     **/
    private static class ProxyFactory<T> implements InvocationHandler {
        private Object object;

        private ProxyFactory() {
        }

        public ProxyFactory(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object invoke = method.invoke(object, args);
            recordMethodMessage(method);
            return invoke;
        }

        private void recordMethodMessage(Method method) {
            System.out.println(method.getName() + "方法被代理执行，执行时间：" + new Date());
        }

        public T getProxyInstance() {
            return (T) Proxy.newProxyInstance(object.getClass().getClassLoader(), new Class<?>[]{BaseService.class}, this);
        }

    }

}
