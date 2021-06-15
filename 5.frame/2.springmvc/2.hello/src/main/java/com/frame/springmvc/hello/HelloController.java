package com.frame.springmvc.hello;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: railgun
 * 2021/6/15 21:29
 * PS: controller
 **/
public class HelloController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
        //ModelAndView 模型和视图
        ModelAndView mv = new ModelAndView();
        //封装对象，放在ModelAndView中。Model
        mv.addObject("msg", "HelloSpringMVC!");
        //封装要跳转的视图，放在ModelAndView中
        //: /WEB-INF/jsp/hello.jsp
        mv.setViewName("hello");
        return mv;
    }

}
