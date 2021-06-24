package com.frame.springboot.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: railgun
 * 2021/6/24 21:05
 * PS: Spring Security 配置
 **/
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * railgun
     * 2021/6/24 21:09
     * PS: 链式编程
     * 授权
     * 授予角色访问路径权限
     **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 首页所有人可以访问，功能页，只能有权限的人才能访问
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/go-to-html/1/**").hasRole("vip1")
                .antMatchers("/go-to-html/2/**").hasRole("vip2")
                .antMatchers("/go-to-html/3/**").hasRole("vip3")
                // 开启登录页面
                .and().formLogin()
                // 开启注销功能
                .and().logout().logoutSuccessUrl("/index")
                // 记住我，以及记住我的 checkbox 的表单提交的 dom 的 name 属性
                .and().rememberMe().rememberMeParameter("remember")
                // 自定义登录页面，以及登录请求地址
                .and().formLogin().loginPage("/login-in").loginProcessingUrl("/login")
                // 用户名密码的提交表单的名称
                .usernameParameter("username").passwordParameter("pwd")
                // 关闭防 csrf 攻击功能
                .and().csrf().disable();
    }

    /**
     * railgun
     * 2021/6/24 21:44
     * PS: 认证
     * 认证用户拥有哪个角色
     **/
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("railgun").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1").and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1", "vip2", "vip3").and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip3");
    }

}
