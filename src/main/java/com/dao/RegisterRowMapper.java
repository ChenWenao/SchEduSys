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
        register.setReg_courseId(resultSet.getInt("reg_courseId"));
        register.setIsEnable(resultSet.getString("isEnable"));
        register.setGrade(resultSet.getFloat("grade"));
        register.setTestScore(resultSet.getFloat("testScore"));
        register.setFinalScore(resultSet.getFloat("finalScore"));
        return register;
    }
}
