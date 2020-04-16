package com.service;


import com.dao.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService{
    @Autowired
    private StudentRepository studentRepository;


    //查
    public Student getStudentById(int studentId) {
        return studentRepository.selectStudentById(studentId);
    }

}