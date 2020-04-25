package com.dao;

import com.bean.Student;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Student student = new Student();
        student.setStudentId(resultSet.getInt("studentId"));
        student.setStudentCode(resultSet.getString("studentCode"));
        student.setStudentDepartId(resultSet.getInt("studentDepartId"));
        student.setStudentDepartName(resultSet.getString("studentDepartName"));
        student.setStudentGender(resultSet.getString("studentGender"));
        student.setStudentNativePlace(resultSet.getString("studentNativePlace"));
        student.setStudentPoliticsStatus(resultSet.getString("studentPoliticsStatus"));
        student.setStudentPhoneNumber(resultSet.getString("studentPhoneNumber"));
        student.setStudentRealName(resultSet.getString("studentRealName"));
        student.setStudentIdCard(resultSet.getString("studentIdCard"));
        student.setStudentEntryTime(resultSet.getDate("studentEntryTime"));
        student.setStudentNote(resultSet.getString("studentNote"));
        return student;
    }
}