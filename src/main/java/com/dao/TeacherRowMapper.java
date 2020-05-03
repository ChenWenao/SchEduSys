package com.dao;

import com.bean.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherRowMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(resultSet.getInt("teacherId"));
        teacher.setTeacherCode(resultSet.getString("teacherCode"));
        teacher.setTeacherDepartId(resultSet.getInt("teacherDepartId"));
        teacher.setTeacherDepartName(resultSet.getString("teacherDepartName"));
        teacher.setTeacherGender(resultSet.getString("teacherGender"));
        teacher.setTeacherNativePlace(resultSet.getString("teacherNativePlace"));
        teacher.setTeacherPoliticsStatus(resultSet.getString("teacherPoliticsStatus"));
        teacher.setTeacherDescription(resultSet.getString("teacherDescription"));
        teacher.setTeacherPhoneNumber(resultSet.getString("teacherPhoneNumber"));
        teacher.setTeacherRealName(resultSet.getString("teacherRealName"));
        teacher.setTeacherIdCard(resultSet.getString("teacherIdCard"));
        teacher.setTeacherEntryTime(resultSet.getDate("teacherEntryTime"));
        return teacher;
    }
}

