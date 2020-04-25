package com.controller;


import com.bean.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController{
    @Autowired
     private UserService userService;

    //删

    //查
    @RequestMapping("User/userById/{userId}")
    public User getUserById(@PathVariable("userId") int userId){
        return userService.getUserById(userId);
    }

    @RequestMapping("User/userByCode/{userCode}")
    public User getUserByCode(@PathVariable("userCode") String userCode){
        return userService.getUserByCode(userCode);
    }
}