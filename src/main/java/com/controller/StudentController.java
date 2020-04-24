package com.controller;


import com.bean.*;
import com.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    //改
    //注销学生
    @PostMapping("Student/dropStudent")
    public boolean dropStudent(@RequestBody List<Integer> studentIds) {
        for (int studentId : studentIds) {
            if (studentService.getStudentById(studentId) == null || !studentService.dropStudent(studentId)) {
                return false;
            }
        }
        return true;
    }
    //恢复学生
    @PostMapping("Student/restoreStudent")
    public boolean restoreStudent(@RequestBody List<Integer> studentIds) {
        for (int studentId : studentIds) {
            if (studentService.getStudentById(studentId) == null || !studentService.restoreStudent(studentId)) {
                return false;
            }
        }
        return true;
    }
    //修改学生信息
    @PostMapping("Student/modifyStudent")
    public boolean modifyStudent(@ModelAttribute(value = "studentId") Student student, Register register) {
        Student student_find = studentService.getStudentById(student.getStudentId());
        if (student_find == null)
            return false;//要修改的student不存在。
        return studentService.modifyStudent(student,register);
    }

    //查
    @RequestMapping("Student/studentById/{studentId}")
    public Student getStudentById(@PathVariable("studentId") int studentId){
        return studentService.getStudentById(studentId);
    }

}