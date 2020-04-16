package com.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdminRepository{
    @Autowired
    private JdbcTemplate template;
    private AdminRowMapper adminRowMapper=new AdminRowMapper();









}