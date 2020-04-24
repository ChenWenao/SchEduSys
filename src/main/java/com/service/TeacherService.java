package com.service;


import com.bean.Admin;
import com.bean.Teacher;
import com.dao.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService{
    @Autowired
    private TeacherRepository teacherRepository;

    //增
    public boolean addNewTeacher(Teacher newTeacher) {
        return teacherRepository.insertANewTeacher(newTeacher);
    }

    //删
    public boolean removeTeacher(int teacherId) {
        return teacherRepository.deleteTeacher(teacherId);
    }

    //查
    public Teacher getTeacherById(int teacherId) {
        return teacherRepository.selectTeacherById(teacherId);
    }



}