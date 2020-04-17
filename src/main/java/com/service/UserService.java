package com.service;


import com.bean.User;
import com.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService{
    @Autowired
    private UserRepository userRepository;

    //查
    public User getUserById(int userId) {
        return userRepository.selectUserById(userId);
    }

}