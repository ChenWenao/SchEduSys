package com.dao;

import com.bean.Register;
import com.bean.Schedule;
import com.bean.Student;
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
            //删除授课
            template.update("delete from courseSchedule where sch_teacherId=?", teacherId);
            //删除teacher
            template.update("delete from Teacher where teacherId=?", teacherId);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //改
    //软删除
    public boolean dropTeacher(int teacherId) {
        try {
            //删除授课
            template.update("update courseSchedule set isEnable='F' where sch_teacherId =?)", teacherId);
            //删除teacher
            template.update("update Teacher set isEnable='F' where teacherId=?", teacherId);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean restoreTeacher(int teacherId) {
        try {
            //恢复授课
            template.update("update courseSchedule set isEnable='T' where sch_teacherId =?)", teacherId);
            //恢复teacher
            template.update("update Teacher set isEnable='T' where teacherId=?", teacherId);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean modifyTeacher(Teacher teacher, Schedule schedule) {
        try {
            //修改Teacher
            template.update("update Teacher set " +
                            "teacherCode=?,"+
                            "teacherDepartId=?,"+
                            "teacherDepartName=?,"+
                            "teacherGender=?,"+
                            "teacherNativePlace=?,"+
                            "teacherPoliticsStatus=?,"+
                            "teacherPhoneNumber=?,"+
                            "teacherRealName=?,"+
                            "teacherIdCard=?,"+
                            "teacherEntryTime=?,"+
                            "where teacherId=?"
                    , teacher.getTeacherCode()
                    , teacher.getTeacherDepartId()
                    , teacher.getTeacherDepartName()
                    , teacher.getTeacherGender()
                    , teacher.getTeacherNativePlace()
                    , teacher.getTeacherPoliticsStatus()
                    , teacher.getTeacherPhoneNumber()
                    , teacher.getTeacherRealName()
                    , teacher.getTeacherIdCard()
                    , teacher.getTeacherEntryTime()
                    , teacher.getTeacherId()
            );
            //修改Schedule
            template.update("update Schedule set sch_teacherId=? where teacherId=?"
                    , schedule.getSch_teacherId()
                    , teacher.getTeacherId());
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