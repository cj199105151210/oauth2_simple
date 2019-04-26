package com.oauth.oau.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String id;

    private String name;

    private String userCode;

    private String username;

    private String password;

    private String email;

    private String phone;

    private String organId;
}
