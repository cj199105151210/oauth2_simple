package com.oauth.oau.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;


import javax.servlet.http.HttpServletResponse;

/**
 * 配置资源服务器
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ResourceServiceConfig extends ResourceServerConfigurerAdapter {

    public void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request,response,authException) -> response.sendError(HttpServletResponse.SC_ACCEPTED) )
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .and().cors().configurationSource(request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setMaxAge(3600L);
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.addAllowedMethod("*");
            corsConfiguration.addAllowedHeader("*");
            corsConfiguration.addAllowedOrigin(request.getHeader("Origin"));
            return corsConfiguration;

        });
    }



}
