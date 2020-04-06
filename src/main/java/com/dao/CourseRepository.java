package com.dao;

import com.bean.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepository {
    @Autowired
    private JdbcTemplate template;//后期用来数据库操作的，这里暂时不演示，不难。

    public Course getOneCourse(){
        Course oneCourse=new Course();
        oneCourse.setCourseId(1);
        oneCourse.setCourseName("数学课");
        return oneCourse;
    }
}
