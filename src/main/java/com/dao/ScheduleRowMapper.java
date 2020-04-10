package com.dao;


import com.bean.Schedule;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ScheduleRowMapper implements RowMapper<Schedule> {
    @Override
    public Schedule mapRow(ResultSet resultSet, int rowNum) throws SQLException{
        Schedule schedule=new Schedule();
        schedule.setScheduleId(resultSet.getInt("scheduleId"));
        schedule.setSch_courseId(resultSet.getInt("sch_courseId"));
        schedule.setSch_teacherId(resultSet.getInt("sch_teacherId"));
        schedule.setIsEnable(resultSet.getString("isEnable"));
        schedule.setPublishScore(resultSet.getString("publishScore"));
        return schedule;
    }
}
