package com.dao;

import com.bean.Course;
import com.bean.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseRepository {
    @Autowired
    private JdbcTemplate template;//后期用来数据库操作的，这里暂时不演示，不难。
    private CourseRowMapper courseRowMapper = new CourseRowMapper();

    //增
    public boolean insertANewCourse(Course newCourse) {
        try {
            template.update("insert into Course(courseName, courseTopicId, courseTopicName,courseDepartId,courseDepartName,coursePeriod,courseCredit,courseStartTime,courseLogo,courseLevel,courseType) values (?,?,?,?,?,?,?,?,?,?,?)"
                    , newCourse.getCourseName()
                    , newCourse.getCourseTopicId()
                    , newCourse.getCourseTopicName()
                    , newCourse.getCourseDepartId()
                    , newCourse.getCourseDepartName()
                    , newCourse.getCoursePeriod()
                    , newCourse.getCourseCredit()
                    , newCourse.getCourseStartTime()
                    , newCourse.getCourseLogo()
                    , newCourse.getCourseLevel()
                    , newCourse.getCourseType());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    //查
    public Course selectCourseById(int courseId) {
        try {
            List<Course> courses = template.query("select * from Course where courseId =?", courseRowMapper, courseId);
            return courses.get(0);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
