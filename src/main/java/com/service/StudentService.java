package com.service;


import com.bean.Admin;
import com.bean.Student;
import com.dao.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService{
    @Autowired
    private StudentRepository studentRepository;

    //增
    public boolean addNewStudent(Student newStudent) {
        return studentRepository.insertANewStudent(newStudent);
    }

    //删
    public boolean removeStudent(int studentId) {
        return studentRepository.deleteStudent(studentId);
    }

    //查
    public Student getStudentById(int studentId) {
        return studentRepository.selectStudentById(studentId);
    }

}