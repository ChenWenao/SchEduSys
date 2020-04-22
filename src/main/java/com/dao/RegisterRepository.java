package com.dao;

import com.bean.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.spec.ECField;
import java.util.List;

@Repository
public class RegisterRepository {
    @Autowired
    private JdbcTemplate template;
    private RegisterRowMapper registerRowMapper=new RegisterRowMapper();

    //增
    public boolean insertANewRegister(int reg_teacherId,int reg_studentId,int reg_courseId){
        try{
            template.update("insert into courseRegister(reg_teacherId,reg_studentId,reg_courseId) values(?,?,?)",reg_teacherId,reg_studentId,reg_courseId);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //删
    public boolean deleteRegister(int reg_studentId,int reg_courseId){
        try{
            template.update("delete from courseRegister where reg_studentId=? and reg_courseId=?",reg_studentId,reg_courseId);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //改

    public boolean updateGrade(int courseId,int studentId,float grade,float testScore,float finalScore){
        try {
            template.update("update courseRegister set grade=? , testScore=? , finalScore=? where reg_courseId=? and reg_studentId=?",grade,testScore,finalScore,courseId,studentId);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }








    //查
    public Register selectScheduleByCourseId(int reg_courseId){
        try {
            List<Register> registers=template.query("select * from Course,Teacher,Student,courseRegister " +
                    "where courseId=reg_courseId and teacherId=reg_teacherId and studentId=reg_studentId and courseId=?",registerRowMapper,reg_courseId);
            return registers.get(0);
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }



}
