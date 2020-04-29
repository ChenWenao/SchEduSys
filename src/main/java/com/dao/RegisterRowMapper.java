package com.dao;

import com.bean.Register;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterRowMapper implements RowMapper<Register> {
    @Override
    public Register mapRow(ResultSet resultSet, int i) throws SQLException {
        Register register = new Register();
        register.setReg_teacherId(resultSet.getInt("reg_teacherId"));
        register.setReg_studentId(resultSet.getInt("reg_studentId"));
        register.setReg_courseId(resultSet.getInt("reg_courseId"));
        register.setIsEnable(resultSet.getString("isEnable"));
        register.setGrade(resultSet.getFloat("grade"));
        register.setTestScore(resultSet.getFloat("testScore"));
        register.setFinalScore(resultSet.getFloat("finalScore"));

        //以下为附加字段：

        //Teacher
        register.setTeacherCode(resultSet.getString("teacherCode"));
        register.setTeacherDepartId(resultSet.getInt("teacherDepartId"));
        register.setTeacherDepartName(resultSet.getString("teacherDepartName"));
        register.setTeacherGender(resultSet.getString("teacherGender"));
        register.setTeacherNativePlace(resultSet.getString("teacherNativePlace"));
        register.setTeacherPoliticsStatus(resultSet.getString("teacherPoliticsStatus"));
        register.setTeacherDescription(resultSet.getString("teacherDescription"));
        register.setTeacherPhoneNumber(resultSet.getString("teacherPhoneNumber"));
        register.setTeacherRealName(resultSet.getString("teacherRealName"));
        register.setTeacherIdCard(resultSet.getString("teacherIdCard"));
        register.setTeacherEntryTime(resultSet.getDate("teacherEntryTime"));

        //Student
        register.setStudentCode(resultSet.getString("studentCode"));
        register.setStudentDepartId(resultSet.getInt("studentDepartId"));
        register.setStudentDepartName(resultSet.getString("studentDepartName"));
        register.setStudentGender(resultSet.getString("studentGender"));
        register.setStudentNativePlace(resultSet.getString("studentNativePlace"));
        register.setStudentPoliticsStatus(resultSet.getString("studentPoliticsStatus"));
        register.setStudentPhoneNumber(resultSet.getString("studentPhoneNumber"));
        register.setStudentRealName(resultSet.getString("studentRealName"));
        register.setStudentIdCard(resultSet.getString("studentIdCard"));
        register.setStudentEntryTime(resultSet.getDate("studentEntryTime"));
        register.setStudentNote(resultSet.getString("studentNote"));
        register.setStudentCreditSum(resultSet.getFloat("studentCreditSum"));

        //Course
        register.setCourseName(resultSet.getString("courseName"));
        register.setCourseTopicId(resultSet.getInt("courseTopicId"));
        register.setCourseTopicName(resultSet.getString("courseTopicName"));
        register.setCourseDepartId(resultSet.getInt("courseDepartId"));
        register.setCourseDepartName((resultSet.getString("courseDepartName")));
        register.setCoursePeriod(resultSet.getInt("coursePeriod"));
        register.setCourseCredit(resultSet.getFloat("courseCredit"));
        register.setCourseStartTime(resultSet.getDate("courseStartTime"));
        register.setCourseLogo(resultSet.getString("courseLogo"));
        register.setCourseLevel(resultSet.getInt("courseLevel"));
        register.setCourseType(resultSet.getString("courseType"));
        register.setCourseDescription(resultSet.getString("courseDescription"));
        register.setCourseFAQ(resultSet.getString("courseFAQ"));
        register.setCourseGradingPolicy(resultSet.getString("courseGradingPolicy"));
        register.setCourseRequirement(resultSet.getString("courseRequirement"));

        return register;
    }
}
