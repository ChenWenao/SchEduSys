package com.dao;

import com.bean.Admin;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class AdminRowMapper implements RowMapper<Admin> {
    @Override
    public Admin mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Admin admin = new Admin();
        admin.setAdminId(resultSet.getInt("adminId"));
        admin.setAdminCode(resultSet.getString("adminCode"));
        admin.setAdminNativePlace(resultSet.getString("adminNativePlace"));
        admin.setAdminGender(resultSet.getString("adminGender"));
        admin.setAdminPoliticsStatus(resultSet.getString("adminPoliticsStatus"));
        admin.setAdminPhoneNumber(resultSet.getString("adminPhoneNumber"));
        admin.setAdminRealName(resultSet.getString("adminRealName"));
        admin.setAdminIdCard(resultSet.getString("adminIdCard"));
        admin.setAdminCreateTime(resultSet.getDate("adminCreateTime"));
        admin.setAdminNote(resultSet.getString("adminNote"));
        return admin;
    }
}