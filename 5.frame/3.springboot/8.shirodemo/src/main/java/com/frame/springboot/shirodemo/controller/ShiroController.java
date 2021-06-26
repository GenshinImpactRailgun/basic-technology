package com.frame.springboot.shirodemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: railgun
 * 2021/6/24 20:29
 * PS:
 **/
@Slf4j
@Controller
public class ShiroController {

    @GetMapping({"", "index"})
    public String index() {
        return "index";
    }

    @GetMapping("login-in")
    public String loginIn() {
        return "login";
    }

    /**
     * railgun
     * 2021/6/26 14:13
     * PS: 未授权页面
     **/
    @GetMapping("unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @GetMapping("go-to-html/{level}/{order}")
    public String goToHtml(@PathVariable("level") int level, @PathVariable("order") int order) {
        return "level" + level + "/" + order;
    }

    @PostMapping("login-in")
    public String postLoginIn(@RequestParam(value = "username") String username
            , @RequestParam(value = "pwd") String pwd, Model model) {
        // 获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
        token.setRememberMe(true);
        try {
            // 执行登录操作
            currentUser.login(token);
            return "index";
        } catch (UnknownAccountException uae) {
            // 用户名不存在
            log.info("There is no user with username of " + token.getPrincipal());
            model.addAttribute("msg", "用户名不存在");
        } catch (IncorrectCredentialsException ice) {
            // 密码错误
            log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            model.addAttribute("msg", "密码错误");
        } catch (LockedAccountException lae) {
            // 用户锁定异常
            log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
            model.addAttribute("msg", "用户锁定异常");
        }
        // ... catch more exceptions here (maybe custom ones specific to your application?
        catch (AuthenticationException ae) {
            // 没有权限，其他异常
            //unexpected condition?  error?
            model.addAttribute("msg", "没有权限，其他异常");
        }
        return "login";
    }

}
