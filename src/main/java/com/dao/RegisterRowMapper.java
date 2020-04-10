package com.dao;

import com.bean.Register;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterRowMapper implements RowMapper<Register> {
    @Override
    public Register mapRow(ResultSet resultSet, int i) throws SQLException {
        Register register=new Register();
        register.setRegisterId(resultSet.getInt("registerId"));
        register.setReg_scheduleId(resultSet.getInt("reg_scheduleId"));
        register.setReg_studentId(resultSet.getInt("reg_studentId"));
        register.setIsEnable(resultSet.getString("isEnable"));
        register.setScore(resultSet.getFloat("score"));
        return register;
    }
}
