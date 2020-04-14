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

    //删
    public boolean deleteCourse(int courseId) {
        try {
            //删除CourseRegister
            template.update("delete from courseRegister where reg_courseId=?",courseId);
            //删除CourseSchedule
            template.update("delete from courseSchedule where sch_courseId=?",courseId);
            //删除Course
            template.update("delete from Course where courseId=?",courseId);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }





    //改
    public boolean dropCourse(int courseId) {
        try {
            //删除CourseRegister
            template.update("update courseRegister set isEnable='F' where courseId=?",courseId);
            //删除CourseSchedule
            template.update("update courseSchedule set isEnable='F' where courseId=?",courseId);
            //删除Course
            template.update("update Course set isEnable='F' where courseId=?", courseId);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean restoreCourse(int courseId){
        try {
            //恢复CourseRegister
            template.update("update courseRegister set isEnable='T' where courseId=?",courseId);
            //恢复CourseSchedule
            template.update("update courseSchedule set isEnable='T' where courseId=?",courseId);
            //恢复Course
            template.update("update Course set isEnable='T' where courseId=?", courseId);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean modifyCourse(Course modifyCourse){
        try {
            template.update("update Course set " +
                    "courseName=?," +
                    "courseTopicId=?," +
                    "courseTopicName=?," +
                    "courseDepartId=?," +
                    "courseDepartName=?," +
                    "coursePeriod=?," +
                    "courseCredit=?," +
                    "courseStartTime=?," +
                    "courseLogo=?," +
                    "courseLevel=?," +
                    "courseType=?," +
                    "courseDescription=?," +
                    "courseFAQ=?," +
                    "courseGradingPolicy=?," +
                    "courseRequirement=?" +
                    "where courseId=?",modifyCourse.getCourseName(),modifyCourse.getCourseTopicId(),modifyCourse.getCourseTopicName(),
                    modifyCourse.getCourseDepartId(),modifyCourse.getCourseDepartName(),modifyCourse.getCoursePeriod(),
                    modifyCourse.getCourseCredit(),modifyCourse.getCourseStartTime(),modifyCourse.getCourseLogo(),
                    modifyCourse.getCourseLevel(),modifyCourse.getCourseType(),modifyCourse.getCourseDescription(),
                    modifyCourse.getCourseFAQ(),modifyCourse.getCourseGradingPolicy(),modifyCourse.getCourseRequirement(),
                    modifyCourse.getCourseId()
                    );
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }


    //查
    public Course selectCourseById(int courseId) {
        try {
            List<Course> courses = template.query("select * from Course where courseId =?", courseRowMapper, courseId);
            return courses.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Course> selectCourses(String order_by,String order){
        return null;
    }
}
