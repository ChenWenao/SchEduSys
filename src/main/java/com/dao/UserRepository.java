package com.dao;


import com.bean.Admin;
import com.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository{
    @Autowired
    private JdbcTemplate template;
    private UserRowMapper userRowMapper=new UserRowMapper();

    //增
    public boolean insertANewUser(User newUser) {
        try {
            template.update("insert into User(userCode,userIdCard,userRealName,userIdentity) values (?,?,?,?)"
                    , newUser.getUserCode()
                    , newUser.getUserIdCard()
                    , newUser.getUserRealName()
                    , newUser.getUserIdentity());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    //删
    public boolean deleteUser(int userId) {
        try {
            template.update("delete from User where userId=?",userId);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //查
    public User selectUserById(int userId) {
        try {
            List<User> users = template.query("select * from User where userId =?", userRowMapper, userId);
            return users.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}