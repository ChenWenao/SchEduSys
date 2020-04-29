package com.dao;

import com.bean.Schedule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleRowMapper implements RowMapper<Schedule> {
    @Override
    public Schedule mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Schedule schedule = new Schedule();
        schedule.setSch_courseId(resultSet.getInt("sch_courseId"));
        schedule.setSch_teacherId(resultSet.getInt("sch_teacherId"));
        schedule.setIsEnable(resultSet.getString("isEnable"));
        schedule.setPublishScore(resultSet.getString("publishScore"));
        schedule.setSelectStartTime(resultSet.getDate("selectStartTime"));
        schedule.setSelectEndTime(resultSet.getDate("selectEndTime"));
        schedule.setScoreStartTime(resultSet.getDate("scoreStartTime"));
        schedule.setScoreEndTime(resultSet.getDate("scoreEndTime"));
        //以下为附加字段：
        //Course
        schedule.setCourseName(resultSet.getString("courseName"));
        schedule.setCourseTopicId(resultSet.getInt("courseTopicId"));
        schedule.setCourseTopicName(resultSet.getString("courseTopicName"));
        schedule.setCourseDepartId(resultSet.getInt("courseDepartId"));
        schedule.setCourseDepartName((resultSet.getString("courseDepartName")));
        schedule.setCoursePeriod(resultSet.getInt("coursePeriod"));
        schedule.setCourseCredit(resultSet.getFloat("courseCredit"));
        schedule.setCourseStartTime(resultSet.getDate("courseStartTime"));
        schedule.setCourseLogo(resultSet.getString("courseLogo"));
        schedule.setCourseLevel(resultSet.getInt("courseLevel"));
        schedule.setCourseType(resultSet.getString("courseType"));
        schedule.setCourseDescription(resultSet.getString("courseDescription"));
        schedule.setCourseFAQ(resultSet.getString("courseFAQ"));
        schedule.setCourseGradingPolicy(resultSet.getString("courseGradingPolicy"));
        schedule.setCourseRequirement(resultSet.getString("courseRequirement"));

        //Teacher
        schedule.setTeacherCode(resultSet.getString("teacherCode"));
        schedule.setTeacherDepartId(resultSet.getInt("teacherDepartId"));
        schedule.setTeacherDepartName(resultSet.getString("teacherDepartName"));
        schedule.setTeacherGender(resultSet.getString("teacherGender"));
        schedule.setTeacherNativePlace(resultSet.getString("teacherNativePlace"));
        schedule.setTeacherPoliticsStatus(resultSet.getString("teacherPoliticsStatus"));
        schedule.setTeacherDescription(resultSet.getString("teacherDescription"));
        schedule.setTeacherPhoneNumber(resultSet.getString("teacherPhoneNumber"));
        schedule.setTeacherRealName(resultSet.getString("teacherRealName"));
        schedule.setTeacherIdCard(resultSet.getString("teacherIdCard"));
        schedule.setTeacherEntryTime(resultSet.getDate("teacherEntryTime"));

        return schedule;
    }
}
