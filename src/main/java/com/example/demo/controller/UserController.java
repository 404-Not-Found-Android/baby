package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.response.SpringBootJSONResult;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register_user", method = {RequestMethod.POST, RequestMethod.GET})
    public SpringBootJSONResult registerUser(@RequestBody User user) {
        SpringBootJSONResult springBootJSONResult = new SpringBootJSONResult();
        try {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            int result = userService.addUser(user);
            if (result == -1) {
                springBootJSONResult.setStatus(500);
                springBootJSONResult.setMsg("用户名已存在");
                return springBootJSONResult;
            }
            springBootJSONResult.setStatus(200);
            springBootJSONResult.setMsg("注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            springBootJSONResult.setStatus(500);
            springBootJSONResult.setMsg(e.getMessage());
            return springBootJSONResult;
        }
        return springBootJSONResult;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public SpringBootJSONResult login(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) {
        SpringBootJSONResult springBootJSONResult = new SpringBootJSONResult();
        User login = userService.login(userName, password);
        if (login != null) {
            springBootJSONResult.setStatus(200);
            springBootJSONResult.setData(login);
            springBootJSONResult.setOk("ok");
            springBootJSONResult.setMsg("login success");
            return springBootJSONResult;
        }
        springBootJSONResult.setStatus(500);
        springBootJSONResult.setMsg("login error");
        springBootJSONResult.setOk("error");
        return springBootJSONResult;
    }

}
