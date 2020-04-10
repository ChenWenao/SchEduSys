package com.service;


import com.bean.Course;
import com.dao.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    //增
    public boolean addNewCourse(Course newCourse) {
        return courseRepository.insertANewCourse(newCourse);
    }

    //删
    public boolean removeCourse(int courseId) {
        return courseRepository.deleteCourse(courseId);
    }


    //改
    public boolean dropCourse(int courseId) {
        return courseRepository.dropCourse(courseId);
    }


    //查
    public Course getCourseById(int courseId) {
        return courseRepository.selectCourseById(courseId);
    }

}
