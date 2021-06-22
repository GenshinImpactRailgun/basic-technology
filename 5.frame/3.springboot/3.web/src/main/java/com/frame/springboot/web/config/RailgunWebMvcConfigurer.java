package com.frame.springboot.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Author: railgun
 * 2021/6/22 22:19
 * PS: 自定义扩展配置类
 **/
@Configuration
//@EnableWebMvc
public class RailgunWebMvcConfigurer implements WebMvcConfigurer {

    /**
     * railgun
     * 2021/6/22 23:03
     * PS: 如果遇到 railgun 注解，则返回 test 的视图
     **/
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("railgun").setViewName("test");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        WebMvcConfigurer.super.configureViewResolvers(registry);
    }

    /**
     * railgun
     * 2021/6/22 22:23
     * PS: 自定义视图解析器注入到 SpringBoot 中
     * DispatcherServlet.doDispatch 该方法断点之后，查看 this 对象中的  viewResolvers 中可以查看到
     **/
    @Bean
    public ViewResolver railgunViewResolver() {
        return new RailgunContentNegotiatingViewResolver();
    }

    /**
     * @Author: railgun
     * 2021/6/22 22:21
     * PS: 自定义视图解析器
     **/
    private static class RailgunContentNegotiatingViewResolver implements ViewResolver {
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }

    /**
     * railgun
     * 2021/6/23 0:56
     * PS: 使得自定义的地址解析器生效
     **/
    @Bean
    public LocaleResolver localeResolver() {
        return new RailgunLocaleResolver();
    }

    /**
     * railgun
     * 2021/6/23 0:52
     * PS: 自定义地区解析器
     **/
    private static class RailgunLocaleResolver implements LocaleResolver {
        @Override
        public Locale resolveLocale(HttpServletRequest request) {
            String language = request.getParameter("l");
            if (!StringUtils.isEmpty(language)) {
                String[] split = language.split("_");
                return new Locale(split[0], split[1]);
            }
            return Locale.getDefault();
        }

        @Override
        public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

        }
    }

}
