package com.dao;

import com.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentRepository {
    @Autowired
    private JdbcTemplate template;
    private StudentRowMapper studentRowMapper = new StudentRowMapper();

    //增
    public boolean insertANewStudent(Student newStudent) {
        try {
            template.update("insert into Student(studentCode,studentDepartId,studentDepartName,studentGender,studentNativePlace,studentPoliticsStatus,studentPhoneNumber,studentRealName,studentIdCard,studentNote) values (?,?,?,?,?,?,?,?,?,?)"
                    , newStudent.getStudentCode()
                    , newStudent.getStudentDepartId()
                    , newStudent.getStudentDepartName()
                    , newStudent.getStudentGender()
                    , newStudent.getStudentNativePlace()
                    , newStudent.getStudentPoliticsStatus()
                    , newStudent.getStudentPhoneNumber()
                    , newStudent.getStudentRealName()
                    , newStudent.getStudentIdCard()
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
            template.update("delete from courseRegister where reg_studentId=?", studentId);
            List<Student> students = template.query("select * from Student where studentId=?", studentRowMapper, studentId);
            //删除student
            template.update("delete from Student where studentId=?", studentId);
            //删除User
            template.update("delete from SchEduSys.User where userCode =?", students.get(0).getStudentCode());
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //改
    //软删除
    public boolean dropStudent(int studentId) {
        try {
            //删除选课
            template.update("update courseRegister set isEnable='F' where reg_studentId =?", studentId);
            List<Student> students = template.query("select * from Student where studentId=?", studentRowMapper, studentId);
            //删除User
            template.update("update User set isEnable='F' where userCode =?", students.get(0).getStudentCode());
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean restoreStudent(int studentId) {
        try {
            //恢复选课
            template.update("update courseRegister set isEnable='T' where reg_studentId =?", studentId);
            List<Student> students = template.query("select * from Student where studentId=?", studentRowMapper, studentId);
            //恢复User
            template.update("update User set isEnable='T' where userCode =?", students.get(0).getStudentCode());
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean modifyStudent(Student modifyStudent) {
        try {
            //修改User
            template.update("update User set " +
                            "userRealName=? " +
                            "where userCode=? "
                    , modifyStudent.getStudentRealName()
                    , modifyStudent.getStudentCode());
            //修改Student
            template.update("update Student set " +
                            "studentDepartId=?," +
                            "studentDepartName=?," +
                            "studentGender=?," +
                            "studentNativePlace=?," +
                            "studentPoliticsStatus=?," +
                            "studentPhoneNumber=?," +
                            "studentRealName=?," +
                            "studentNote=? " +
                            "where studentId=?"
                    , modifyStudent.getStudentDepartId()
                    , modifyStudent.getStudentDepartName()
                    , modifyStudent.getStudentGender()
                    , modifyStudent.getStudentNativePlace()
                    , modifyStudent.getStudentPoliticsStatus()
                    , modifyStudent.getStudentPhoneNumber()
                    , modifyStudent.getStudentRealName()
                    , modifyStudent.getStudentNote()
                    , modifyStudent.getStudentId()
            );
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

    public Student selectStudentByCode(String studentCode) {
        try {
            List<Student> students = template.query("select * from Student where studentCode =?", studentRowMapper, studentCode);
            return students.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Student> selectStudents(String isEnable, String order_by, String order,int page,int pageSize) {
        try {
            String sql = "select * from Student,User where studentCode=userCode  ";
            if ("on".equals(isEnable))
                sql += "and isEnable='T' order by ";
            else if ("off".equals(isEnable))
                sql += "and isEnable='F' order by ";
            else
                sql += "order by ";
            sql += order_by;
            if ("0".equals(order))
                sql += " desc";
            sql += " limit " + (page - 1) * pageSize + "," + pageSize;
            List<Student> students = template.query(sql, studentRowMapper);
            return students;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }


}