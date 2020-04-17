package com.dao;


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