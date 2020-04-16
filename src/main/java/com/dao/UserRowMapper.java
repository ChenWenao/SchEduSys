package com.dao;

import com.bean.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(resultSet.getInt("userId"));
        user.setUserCode(resultSet.getString("userCode"));
        user.setUserPassword(resultSet.getString("userPassword"));
        user.setIsEnable(resultSet.getString("isEnable"));
        user.setUserIdCard(resultSet.getString("userIdCard"));
        user.setUserRealName(resultSet.getString("userRealName"));
        user.setUserIdentity(resultSet.getString("userIdentity"));
        return user;
    }
}