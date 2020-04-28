package com.chen.shiro.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * shiro配置
 * <p>
 * @Author LeifChen
 * @Date 2020-04-27
 */
@Configuration
public class ShiroConfig {

    /**
     * 安全管理器
     * @return
     */
    @Bean
    @DependsOn("userRepository")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        return securityManager;
    }

    /**
     * 身份认证器
     * @return
     */
    @Bean
    public ShiroRealm shiroRealm(){
        return new ShiroRealm();
    }

    /**
     * shiro过滤器
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // shiro拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 配置不被拦截的资源及链接
        filterChainDefinitionMap.put("/static/**", "anon");
        // 退出过滤器
        filterChainDefinitionMap.put("/logout", "logout");
        // 配置需要认证权限
        filterChainDefinitionMap.put("/**", "authc");
        // 默认登录链接
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后跳转链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权的跳转链接
        shiroFilterFactoryBean.setUnauthorizedUrl("/401");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 开启shiro的AOP注解
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 处理异常信息
     * @return
     */
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        // 数据库异常处理
        mappings.setProperty("DatabaseException", "databaseError");
        // 未认证异常
        mappings.setProperty("UnauthorizedException", "401");
        simpleMappingExceptionResolver.setExceptionMappings(mappings);
        simpleMappingExceptionResolver.setDefaultErrorView("error");
        simpleMappingExceptionResolver.setExceptionAttribute("ex");
        return simpleMappingExceptionResolver;
    }
}
