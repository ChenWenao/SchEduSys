package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterRepository {
    @Autowired
    private JdbcTemplate template;
    private RegisterRowMapper registerRowMapper=new RegisterRowMapper();

    public boolean insertANewRegister(int reg_teacherId,int reg_studentId,int reg_courseId){
        try{
            template.update("insert into courseRegister(reg_teacherId,reg_studentId,reg_courseId) values(?,?,?)",reg_teacherId,reg_studentId,reg_courseId);
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }




}
