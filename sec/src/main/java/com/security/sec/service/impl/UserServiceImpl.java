package com.security.sec.service.impl;

import com.security.sec.dao.UserMapper;
import com.security.sec.entity.User;
import com.security.sec.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByName(String s) {
        return userMapper.getByName(s);
    }
}
