package com.service;


import com.dao.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService{
    @Autowired
    private TeacherRepository teacherRepository;

    //查
    public Teacher getTeacherById(int teacherId) {
        return teacherRepository.selectTeacherById(teacherId);
    }



}