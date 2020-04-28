package com.dao;

import com.bean.Student;
import com.bean.User;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate template;
    private UserRowMapper userRowMapper = new UserRowMapper();

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
            template.update("delete from User where userId=?", userId);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteUserByCode(String userCode) {
        try {
            template.update("delete from User where userCode=?", userCode);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //改
    public boolean modifyPassword(User modifyUser) {
        try {
            //修改User
            template.update("update User set " +
                            "userPassword=? " +
                            "where userCode=? "
                    , modifyUser.getUserPassword()
                    , modifyUser.getUserCode());
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

    public User selectUserByCode(String userCode) {
        try {
            List<User> users = template.query("select * from User where userCode =?", userRowMapper, userCode);
            return users.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //登录
    public User findUser(String userCode, String userPassword){
        try {
            List<User> users = template.query("select * from User where userCode =? and userPassword=?", userRowMapper, userCode,userPassword);
            return users.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //找回密码
    public User findResetUser(String userIdCard,String userRealName,String userIdentity ){
        try {
            List<User> users = template.query("select * from User where userRealName =? and userIdCard=? and userIdentity=?"
                    , userRowMapper
                    , userRealName
                    ,userIdCard,
                    userIdentity);
            return users.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}