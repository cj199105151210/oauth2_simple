package com.security.sec.controller;


import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Component
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/current")
    public String getCurrentUser(Principal principal){
        System.out.println(principal);
        return "权限是xxx";
    }

    /**
     * 客户端根据token获取用户
     * @param principal
     * @return
     */
    @RequestMapping("/me")
    public Principal user2(OAuth2Authentication principal){
        return principal;

    }

}
