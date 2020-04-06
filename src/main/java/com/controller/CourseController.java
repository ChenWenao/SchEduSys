package com.controller;

import com.bean.Course;
import com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/SchEduSys/Course/index")
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("index");
        mav.addObject("oneCourse",courseService.getNewCourse());
        return mav;
    }

}

