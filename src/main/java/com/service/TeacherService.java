package com.service;


import com.bean.*;
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

    //改
    public boolean dropTeacher(int teacherId) {
        return teacherRepository.dropTeacher(teacherId);
    }

    public boolean restoreTeacher(int teacherId) { return teacherRepository.restoreTeacher(teacherId); }

    public boolean modifyTeacher(Teacher teacher, Schedule schedule) {
        return teacherRepository.modifyTeacher(teacher, schedule);
    }

    //查
    public Teacher getTeacherById(int teacherId) {
        return teacherRepository.selectTeacherById(teacherId);
    }



}