package com.frame.spring.proxy.demo2;

import com.frame.spring.proxy.demo2.entity.Mosquito;

/**
 * @Author: railgun
 * 2021/6/9 20:37
 * PS: 客户端
 **/
public class Client {
    public static void main(String[] args) {
        MosquitoServiceImpl mosquitoService = new MosquitoServiceImpl();
        BaseServiceProxyImpl<Mosquito> baseServiceProxy = new BaseServiceProxyImpl<>();
        baseServiceProxy.setBaseService(mosquitoService);
        baseServiceProxy.insert();
    }
}
