package com.security.sec.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class User implements Serializable {
    private String id;

    private String name;

    private String userCode;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String organId;
}
