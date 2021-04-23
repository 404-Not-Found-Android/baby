package com.example.demo.service;

import com.example.demo.pojo.User;

public interface UserService {
    int addUser(User user);

    User login(String userName, String password);
}
