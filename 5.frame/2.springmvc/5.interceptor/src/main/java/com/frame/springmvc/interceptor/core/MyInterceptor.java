package com.frame.springmvc.interceptor.core;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: railgun
 * 2021/6/19 23:16
 * PS: 自定义过滤器
 **/
public class MyInterceptor implements HandlerInterceptor {

    private static final String URL_WITHOUT_AUTHORITY = "-login-";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("----------------- 处理前 -----------------");
        String requestURI = request.getRequestURI();
        if (requestURI.contains(URL_WITHOUT_AUTHORITY)) {
            // 请求的路劲不需要权限，放行
            return true;
        } else {
            // 进行业务处理
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("----------------- 处理后 -----------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("----------------- 清理 -----------------");
    }
}
