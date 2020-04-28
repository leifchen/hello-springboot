package com.chen.security.config;

import com.chen.security.model.Menu;
import com.chen.security.model.Role;
import com.chen.security.repository.RoleRepository;
import com.chen.security.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import java.util.List;

/**
 * Security配置
 * <p>
 * @Author LeifChen
 * @Date 2020-04-28
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers("/css/**", "/index").permitAll()
                .antMatchers("/select").hasRole("test")
                .antMatchers("/delete").hasRole("admin");

        // 动态加载数据库中的角色权限
        List<Role> roleList = roleRepository.findAll();
        for (Role role : roleList) {
            for (Menu menu : role.getMenuList()) {
                // Spring Security权限校验时，会自动在权限前面增加ROLE_，需要截取处理
                String roleName = role.getRoleName().replace("ROLE_", "");
                String menuName = "/" + menu.getMenuName();
                httpSecurity
                        .authorizeRequests()
                        .antMatchers(menuName)
                        .hasRole(roleName);
            }
        }

        httpSecurity
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login_error")
                .successForwardUrl("/");
        // 登录异常401
        httpSecurity
                .exceptionHandling().accessDeniedPage("/401");
        httpSecurity
                .logout().logoutSuccessUrl("/logout");
    }

    @Bean
    public static NoOpPasswordEncoder passwordEncoder(){
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    /**
     * 根据用户名密码实现登录
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .inMemoryAuthentication()
                .withUser("test").password("test").roles("TEST")
                .and()
                .withUser("admin").password("admin").roles("ADMIN","TEST");
        authenticationManagerBuilder.userDetailsService(userDetailServiceImpl);
    }
}
