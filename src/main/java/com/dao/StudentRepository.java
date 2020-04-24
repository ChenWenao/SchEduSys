package com.dao;


import com.bean.Admin;
import com.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository{
    @Autowired
    private JdbcTemplate template;
    private StudentRowMapper studentRowMapper=new StudentRowMapper();

    //增
    public boolean insertANewStudent(Student newStudent) {
        try {
            template.update("insert into Student(studentCode,studentDepartId,studentDepartName,studentGender,studentNativePlace,studentPoliticsStatus,studentPhoneNumber,studentRealName,studentIdCard,studentEntryTime,studentNote) values (?,?,?,?,?,?,?,?,?,?,?)"
                    , newStudent.getStudentCode()
                    , newStudent.getStudentDepartId()
                    , newStudent.getStudentDepartName()
                    , newStudent.getStudentGender()
                    , newStudent.getStudentNativePlace()
                    , newStudent.getStudentPoliticsStatus()
                    , newStudent.getStudentPhoneNumber()
                    , newStudent.getStudentRealName()
                    , newStudent.getStudentIdCard()
                    , newStudent.getStudentEntryTime()
                    , newStudent.getStudentNote());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    //删
    public boolean deleteStudent(int studentId) {
        try {
            //删除选课
            template.update("delete from courseregister where reg_studentId=?", studentId);
            //删除student
            template.update("delete from Student where studentId=?", studentId);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //查
    public Student selectStudentById(int studentId) {
        try {
            List<Student> students = template.query("select * from Student where studentId =?", studentRowMapper, studentId);
            return students.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}