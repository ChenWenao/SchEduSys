package com.controller;

import com.bean.Teacher;
import com.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController{
    @Autowired
    private TeacherService teacherService;

    //查
    @RequestMapping("/SchEduSys/Teacher/teacherById/{teacherId}")
    public Teacher getTeacherById(@PathVariable("teacherId") int teacherId){
        return teacherService.getTeacherById(teacherId);
    }


}