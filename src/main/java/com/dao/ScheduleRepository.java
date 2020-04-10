package com.dao;

import com.bean.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleRepository {
    @Autowired
    private JdbcTemplate template;
    private ScheduleRowMapper scheduleRowMapper=new ScheduleRowMapper();

    //增


    //删


    //改


    //查


}
