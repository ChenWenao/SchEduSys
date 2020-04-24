package com.dao;

import com.bean.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Course course = new Course();
        course.setCourseId(resultSet.getInt("courseId"));
        course.setIsEnable(resultSet.getString("isEnable"));
        course.setHaveTeacher(resultSet.getString("haveTeacher"));
        course.setCourseName(resultSet.getString("courseName"));
        course.setCourseTopicId(resultSet.getInt("courseTopicId"));
        course.setCourseTopicName(resultSet.getString("courseTopicName"));
        course.setCourseDepartId(resultSet.getInt("courseDepartId"));
        course.setCourseDepartName((resultSet.getString("courseDepartName")));
        course.setCoursePeriod(resultSet.getInt("coursePeriod"));
        course.setCourseCredit(resultSet.getFloat("courseCredit"));
        course.setCourseStartTime(resultSet.getDate("courseStartTime"));
        course.setCourseLogo(resultSet.getString("courseLogo"));
        course.setCourseLevel(resultSet.getInt("courseLevel"));
        course.setCourseType(resultSet.getString("courseType"));
        course.setCourseDescription(resultSet.getString("courseDescription"));
        course.setCourseFAQ(resultSet.getString("courseFAQ"));
        course.setCourseGradingPolicy(resultSet.getString("courseGradingPolicy"));
        course.setCourseRequirement(resultSet.getString("courseRequirement"));
        return course;
    }
}
