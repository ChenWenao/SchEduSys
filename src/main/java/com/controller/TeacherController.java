package com.controller;

import com.bean.Register;
import com.bean.Schedule;
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

    //改
    //注销教师
    @PostMapping("Teacher/dropTeacher")
    public boolean dropTeacher(@RequestBody List<Integer> teacherIds) {
        for (int teacherId : teacherIds) {
            if (teacherService.getTeacherById(teacherId) == null || !teacherService.dropTeacher(teacherId)) {
                return false;
            }
        }
        return true;
    }
    //恢复教师
    @PostMapping("Teacher/dropTeacher")
    public boolean restoreTeacher(@RequestBody List<Integer> teacherIds) {
        for (int teacherId : teacherIds) {
            if (teacherService.getTeacherById(teacherId) == null || !teacherService.restoreTeacher(teacherId)) {
                return false;
            }
        }
        return true;
    }
    //修改教师信息
    @PostMapping("Teacher/dropTeacher")
    public boolean modifyTeacher(@ModelAttribute(value = "teacherId") Teacher teacher, Schedule schedule) {
        Teacher teacher_find = teacherService.getTeacherById(teacher.getTeacherId());
        if (teacher_find == null)
            return false;//要修改的teacher不存在。
        return teacherService.modifyTeacher(teacher,schedule);
    }

    //查
    @RequestMapping("Teacher/teacherById/{teacherId}")
    public Teacher getTeacherById(@PathVariable("teacherId") int teacherId){
        return teacherService.getTeacherById(teacherId);
    }


}