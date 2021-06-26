package com.frame.springboot.shirodemo.config;

import com.frame.springboot.shirodemo.pojo.UserTest;
import com.frame.springboot.shirodemo.service.inter.UserTestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Repository;

/**
 * @Author: railgun
 * 2021/6/26 12:03
 * PS: 自定义 realm 对象
 **/
@Slf4j
@Repository(value = "shiroRealm")
public class ShiroRealm extends AuthorizingRealm {

    private final UserTestService userTestService;

    public ShiroRealm(UserTestService userTestService) {
        this.userTestService = userTestService;
    }

    /**
     * railgun
     * 2021/6/26 12:04
     * PS: 授权
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("【doGetAuthorizationInfo】【执行了授权】");

        // 获取当前登录人
        Subject subject = SecurityUtils.getSubject();
        UserTest principal = (UserTest) subject.getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 添加权限
        info.addStringPermission(principal.getPerms());
        return info;
    }

    /**
     * railgun
     * 2021/6/26 12:04
     * PS: 认证
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("【doGetAuthenticationInfo】【执行了认证】");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 用户名 密码 数据库中取出校验
        UserTest userByUsername = userTestService.getUserByUsername(token.getUsername());
        if (null == userByUsername) {
            // 抛出异常 UnknownAccountException 【用户名不存在异常】
            return null;
        }
        // 密码验证， shiro 进行校验【MD5 加密】【MD5 盐值加密】
        // 第一个参数 principal 含义为 将该对象塞入到当前对象中，方便授权方法种中获取到该对象
        return new SimpleAuthenticationInfo(userByUsername, userByUsername.getPassword(), "");
    }
}
