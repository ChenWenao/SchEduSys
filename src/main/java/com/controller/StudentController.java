package com.controller;


import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController{
    @Autowired
    private StudentService studentService;


    //查
    @RequestMapping("/SchEduSys/Student/studentById/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId){
        return studentService.getStudentById(studentId);
    }

}