package com.security.sec.auth.config;

import com.security.sec.auth.CustomerAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.annotation.Resource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class WebSecurityAdaptConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "signUserDetailService")
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Resource(name = "myCustomerAuthenticationProvider")
    private CustomerAuthenticationProvider customerAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()//定义哪些url需要保护 哪些不需要保护
                .antMatchers("/oauth/token","oauth/check_token").permitAll() //定义这两个链接不需要登陆可访问
                .antMatchers("/**").permitAll() //定义所有的都不需要登陆，目前是测试需要
                .anyRequest().authenticated()   //其他的都需要登陆
                .and()
                .formLogin().loginPage("/login") //如果未登陆则跳转登陆的页面 这儿 可以控制登陆成功和登陆失败跳转的页面
                .usernameParameter("username").passwordParameter("password").permitAll() //定义号码和密码的parameter
                .and()
                .csrf().disable();  //防止跨站请求
    }

    /**
     * 权限管理器    AuthorizationServerConfigureAdapter 认证中心需要 的AuthenticationManager需要
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //目的是为了前端获取数据时获取到整个form-data的数据，提供验证器
        auth.authenticationProvider(customerAuthenticationProvider);
        //配置登陆user验证处理器，以及密码加密器 好让认证中心进行验证
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return authenticationManager();
    }




}
