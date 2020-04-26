package com.service;


import com.bean.Admin;
import com.bean.Course;
import com.bean.Register;
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

    //改
    public boolean dropStudent(int studentId) {
        return studentRepository.dropStudent(studentId);
    }

    public boolean restoreStudent(int studentId) {
        return studentRepository.restoreStudent(studentId);
    }

    public boolean modifyStudent(Student modifyStudent) {
        return studentRepository.modifyStudent(modifyStudent);
    }

    //查
    public Student getStudentById(int studentId) {
        return studentRepository.selectStudentById(studentId);
    }

    public Student getStudentByCode(String studentCode) {
        return studentRepository.selectStudentByCode(studentCode);
    }

}