package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.response.SpringBootJSONResult;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register_user", method = {RequestMethod.POST, RequestMethod.GET})
    public SpringBootJSONResult registerUser(@RequestBody User user) {
        try {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            userService.addUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return SpringBootJSONResult.errorMsg(e.getMessage());
        }
        return SpringBootJSONResult.ok();
    }

}
