package com.dao;


import com.bean.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepository{
    @Autowired
    private JdbcTemplate template;
    private AdminRowMapper adminRowMapper=new AdminRowMapper();


    //查
    public Admin selectAdminById(int adminId) {
        try {
            List<Admin> admins = template.query("select * from Admin where adminId =?", adminRowMapper, adminId);
            return admins.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}