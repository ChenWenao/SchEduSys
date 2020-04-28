package com.service;


import com.bean.Admin;
import com.bean.User;
import com.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.sql.SQLException;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    //增
    public boolean addNewUser(User newUser) {
        return userRepository.insertANewUser(newUser) ;
    }

    //删
    public boolean removeUser(int userId) {
        return userRepository.deleteUser(userId);
    }

    public boolean removeUserByCode(String userCode){
        return userRepository.deleteUserByCode(userCode);
    }

    //改
    public boolean modifyPassword(User modifyUser){ return userRepository.modifyPassword(modifyUser);};

    //查
    public User getUserById(int userId) {
        return userRepository.selectUserById(userId);
    }

    public User getUserByCode(String  userCode){ return  userRepository.selectUserByCode(userCode);}

    //登录
    public User login(String userCode, String userPassword) { return userRepository.findUser(userCode,userPassword); }

    //找回密码
    public User retrievePassword(String userRealName, String userIdCard){
        return userRepository.findRpUser(userRealName, userIdCard);
    }
}