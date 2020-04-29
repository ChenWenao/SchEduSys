package com.dao;

import com.bean.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartRowMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Department department = new Department();
        department.setDepartId(resultSet.getInt("departId"));
        department.setDepartName(resultSet.getString("departName"));
        department.setIsEnable(resultSet.getString("isEnable"));
        department.setDepartCreateTime(resultSet.getDate("departCreateTime"));
        department.setDepartDescription(resultSet.getString("departDescription"));
        return department;
    }
}
