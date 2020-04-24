package com.controller;


import com.bean.Admin;
import com.bean.Student;
import com.bean.User;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController{
    @Autowired
    private StudentService studentService;

    //增
    @RequestMapping("Student/newStudent")
    public boolean addNewStudent(@ModelAttribute(value = "newStudent") Student newStudent) {
        return studentService.addNewStudent(newStudent);
    }

    //删
    @RequestMapping("Student/removeStudent")
    public boolean removeStudent(@RequestBody List<Integer> studentIds) {
        for (int studentId : studentIds) {
            if (!studentService.removeStudent(studentId)) {
                return false;
            }
        }
        return true;
    }

    //查
    @RequestMapping("Student/studentById/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId){
        return studentService.getStudentById(studentId);
    }

}