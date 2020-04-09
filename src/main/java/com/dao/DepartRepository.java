package com.dao;

import com.bean.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DepartRepository {
    @Autowired
    JdbcTemplate template;
    DepartRowMapper departRowMapper=new DepartRowMapper();

    //增
    public boolean insertANewDepart(Department newDepartment){
        try {
            template.update("insert into Department(departName,departCreateTime,departDescription) values (?,?,?)"
            ,newDepartment.getDepartName()
            ,newDepartment.getDepartCreateTime()
            ,newDepartment.getDepartDescription());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //删

    //改

    //查

}
