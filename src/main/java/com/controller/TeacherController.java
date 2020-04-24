package com.controller;

import com.bean.Student;
import com.bean.Teacher;
import com.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController{
    @Autowired
    private TeacherService teacherService;

    //增
    @RequestMapping("Teacher/newTeacher")
    public boolean addNewTeacher(@ModelAttribute(value = "newTeacher") Teacher newTeacher) {
        return teacherService.addNewTeacher(newTeacher);
    }

    //删
    @RequestMapping("Teacher/removeTeacher")
    public boolean removeAdmin(@RequestBody List<Integer> teacherIds) {
        for (int teacherId : teacherIds) {
            if (!teacherService.removeTeacher(teacherId)) {
                return false;
            }
        }
        return true;
    }

    //查
    @RequestMapping("Teacher/teacherById/{teacherId}")
    public Teacher getTeacherById(@PathVariable("teacherId") int teacherId){
        return teacherService.getTeacherById(teacherId);
    }


}