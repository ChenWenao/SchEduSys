package com.controller;


import com.bean.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController{
    @Autowired
     private UserService userService;

    //删
    @RequestMapping("User/removeUser")
    public boolean removeUser(@RequestBody List<Integer> userIds) {
        for (int userId : userIds) {
            if (!userService.removeUser(userId)) {
                return false;
            }
        }
        return true;
    }

    //查
    @RequestMapping("User/userById/{userId}")
    public User getUserById(@PathVariable("userId") int userId){
        return userService.getUserById(userId);
    }
}