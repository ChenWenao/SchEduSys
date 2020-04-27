package com.dao;

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
            template.update("insert into Teacher(teacherCode,teacherDepartId,teacherDepartName,teacherGender,teacherNativePlace,teacherPoliticsStatus,teacherPhoneNumber,teacherRealName,teacherIdCard,teacherDescription) values (?,?,?,?,?,?,?,?,?,?)"
                    , newTeacher.getTeacherCode()
                    , newTeacher.getTeacherDepartId()
                    , newTeacher.getTeacherDepartName()
                    , newTeacher.getTeacherGender()
                    , newTeacher.getTeacherNativePlace()
                    , newTeacher.getTeacherPoliticsStatus()
                    , newTeacher.getTeacherPhoneNumber()
                    , newTeacher.getTeacherRealName()
                    , newTeacher.getTeacherIdCard()
                    , newTeacher.getTeacherDescription());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    //删
    public boolean deleteTeacher(int teacherId) {
        try {
            //删除选课
            template.update("delete from courseRegister where reg_teacherId=?", teacherId);
            //删除授课
            template.update("delete from courseSchedule where sch_teacherId=?", teacherId);
            List<Teacher> teachers=template.query("select * from Teacher where teacherId=?",teacherRowMapper,teacherId);
            //删除teacher
            template.update("delete from Teacher where teacherId=?", teacherId);
            //删除User
            template.update("delete from User where userCode =?", teachers.get(0).getTeacherCode());
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
            //删除选课
            template.update("update courseRegister set isEnable='F' where reg_teacherId =?", teacherId);
            //删除授课
            template.update("update courseSchedule set isEnable='F' where sch_teacherId =?", teacherId);
            //删除User
            List<Teacher> teachers=template.query("select * from Teacher where teacherId=?",teacherRowMapper,teacherId);
            template.update("update User set isEnable='F' where userCode=?", teachers.get(0).getTeacherCode());
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean restoreTeacher(int teacherId) {
        try {
            //恢复选课
            template.update("update courseRegister set isEnable='T' where reg_teacherId =?", teacherId);
            //恢复授课
            template.update("update courseSchedule set isEnable='T' where sch_teacherId =?", teacherId);
            //删除User
            List<Teacher> teachers=template.query("select * from Teacher where teacherId=?",teacherRowMapper,teacherId);
            template.update("update User set isEnable='T' where userCode =?", teachers.get(0).getTeacherCode());
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean modifyTeacher(Teacher modifyTeacher) {
        try {
            //修改User
            template.update("update User set " +
                            "userRealName=? " +
                            "where userCode=? "
                    , modifyTeacher.getTeacherRealName()
                    , modifyTeacher.getTeacherCode());
            //修改Teacher
            template.update("update Teacher set " +
                            "teacherDepartId=?," +
                            "teacherDepartName=?," +
                            "teacherGender=?," +
                            "teacherNativePlace=?," +
                            "teacherPoliticsStatus=?," +
                            "teacherPhoneNumber=?," +
                            "teacherRealName=? " +
                            "where teacherId=?"
                    , modifyTeacher.getTeacherDepartId()
                    , modifyTeacher.getTeacherDepartName()
                    , modifyTeacher.getTeacherGender()
                    , modifyTeacher.getTeacherNativePlace()
                    , modifyTeacher.getTeacherPoliticsStatus()
                    , modifyTeacher.getTeacherPhoneNumber()
                    , modifyTeacher.getTeacherRealName()
                    , modifyTeacher.getTeacherId()
            );
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
    public Teacher selectTeacherByCode(String teacherCode) {
        try {
            List<Teacher> teachers = template.query("select * from Teacher where teacherCode =?", teacherRowMapper, teacherCode);
            return teachers.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Teacher> selectTeachers(String isEnable, String order_by, String order) {
        try {
            String sql = "select * from Teacher,User where teacherCode=userCode  ";
            if ("on".equals(isEnable))
                sql += "and isEnable='T' order by ";
            else if ("off".equals(isEnable))
                sql += "and isEnable='F' order by ";
            else
                sql+="order by ";
            sql +=order_by;
            if ("0".equals(order))
                sql += " desc";
            List<Teacher> teachers = template.query(sql, teacherRowMapper);
            return teachers;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}