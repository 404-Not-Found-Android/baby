package com.example.demo.service.impl;

import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        User checkUser = userMapper.selectByUserName(user.getUserName());
        if (checkUser != null) {
            return -1;
        }
        return userMapper.insert(user);
    }

    @Override
    public User login(String userName, String password) {
        User user = userMapper.selectByUserName(userName);
        if (user != null) {
            String pwd = user.getPassword();
            if (password.equals(pwd)) {
                return user;
            }
        }
        return null;
    }
}
