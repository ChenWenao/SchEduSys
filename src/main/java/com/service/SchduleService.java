package com.service;

import com.bean.Schedule;
import com.dao.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    //增
    public boolean addNewSchedule(int sch_courseId,int sch_teacherId){
        return scheduleRepository.insertANewSchedule(sch_courseId,sch_teacherId);
    }

    //删
    public boolean removeSchedule(int sch_courseId){
        return scheduleRepository.deleteSchedule(sch_courseId);
    }

    //改
    public boolean dropSchedule(int sch_courseId){
        return scheduleRepository.dropSchedule(sch_courseId);
    }

    public boolean restoreSchedule(int sch_courseId){
        return scheduleRepository.restoreSchedule(sch_courseId);
    }

    public boolean publishScore(int courseId){
        return scheduleRepository.publishScore(courseId);
    }

    //查
    public Schedule getScheduleById(String order_by, int order_value){
        return scheduleRepository.selectScheduleById(order_by,order_value);
    }

    public List<Schedule> getSchedules(String isEnable,String order_by,String order){
        return scheduleRepository.selectSchedules(isEnable,order_by,order);
    }


}
