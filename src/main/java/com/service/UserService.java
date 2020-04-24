package com.service;


import com.bean.Admin;
import com.bean.User;
import com.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    //查
    public User getUserById(int userId) {
        return userRepository.selectUserById(userId);
    }

}