package com.frame.springboot.shirodemo.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: railgun
 * 2021/6/26 11:36
 * PS: shiro 自定义配置
 **/
@Configuration
public class ShiroConfig {

    /**
     * 2021/6/26 12:39 @railgun
     * anon     无需认证就可以访问
     * authc    必须认证了才能访问
     * user     必须拥有记住我功能才能使用
     * perms    拥有对某个资源的权限才能额访问
     * role     拥有某个角色权限才能访问
     **/
    private static final String ANON = "anon";
    private static final String AUTHC = "authc";
    private static final String USER = "user";
    private static final String PERMS = "perms";
    private static final String ROLE = "role";

    /**
     * railgun
     * 2021/6/26 11:37
     * PS: ShiroFilterFactoryBean
     * 用户
     **/
    @Bean(value = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier(value = "defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 设置拦截【设置路径权限过滤】
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/go-to-html/1/*", "perms[user:level1]");
        filterChainDefinitionMap.put("/go-to-html/2/*", "perms[user:level2]");
        filterChainDefinitionMap.put("/go-to-html/3/*", "perms[user:level3]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        // 设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login-in");
        // 未授权页面自定义
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        return shiroFilterFactoryBean;
    }

    /**
     * railgun
     * 2021/6/26 11:37
     * PS: defaultWebSecurityManager
     * 管理 用户
     **/
    @Bean(value = "defaultWebSecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier(value = "shiroRealm") ShiroRealm shiroRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 关联 realm 对象
        defaultWebSecurityManager.setRealm(shiroRealm);
        return defaultWebSecurityManager;
    }

    /**
     * railgun
     * 2021/6/26 11:38
     * PS: 创建 Realm 对象，自定义 Realm 对象
     * 【放到两外一个文件中】
     **/
    /*@Bean(value = "shiroRealm")
    public ShiroRealm getShiroRealm() {
        return new ShiroRealm();
    }*/

}
