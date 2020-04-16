package com.dao;

import com.bean.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeacherRepository {
    @Autowired
    private JdbcTemplate template;
    private TeacherRowMapper teacherRowMapper = new TeacherRowMapper();

    //增
    public boolean insertANewTeacher(Teacher newTeacher) {
        try {
            template.update("insert into Teacher(teacherCode,teacherDepartId,teacherDepartName,teacherGender,teacherNativePlace,teacherPoliticsStatus,teacherPhoneNumber,teacherRealName,teacherIdCard,teacherEntryTime) values (?,?,?,?,?,?,?,?,?,?)"
                    , newTeacher.getTeacherCode()
                    , newTeacher.getTeacherDepartId()
                    , newTeacher.getTeacherDepartName()
                    , newTeacher.getTeacherGender()
                    , newTeacher.getTeacherNativePlace()
                    , newTeacher.getTeacherPoliticsStatus()
                    , newTeacher.getTeacherPhoneNumber()
                    , newTeacher.getTeacherRealName()
                    , newTeacher.getTeacherIdCard()
                    , newTeacher.getTeacherEntryTime());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    //删
    public boolean deleteTeacher(int teacherId) {
        try {
            //删除TeacherSchedule
            template.update("delete from courseSchedule where sch_teacherId=?", teacherId);
            //删除Teacher
            template.update("delete from Teacher where teacherId=?", teacherId);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //查
    public Teacher selectTeacherById(int teacherId) {
        try {
            List<Teacher> teachers = template.query("select * from Teacher where teacherId =?", teacherRowMapper, teacherId);
            return teachers.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}