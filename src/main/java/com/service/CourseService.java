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

    public Course getNewCourse(){
        return courseRepository.getOneCourse();
    }
}
