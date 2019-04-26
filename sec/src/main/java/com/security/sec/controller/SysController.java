package com.security.sec.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/sys")
public class SysController {

    @GetMapping("/permission")
    public String listPermissions(Principal principal){
        System.out.println("999");
        return "666";
    }
}
