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

    public boolean publishScoreOff(int courseId){
        return scheduleRepository.publishScoreOff(courseId);
    }

    //查
    public Schedule getScheduleByCourseId(int courseId){
        return scheduleRepository.selectScheduleByCourseId(courseId);
    }

    public List<Schedule> getScheduleByTeacherId(String teacherCode){
        return scheduleRepository.selectScheduleByTeacherId(teacherCode);
    }

    public List<Schedule> getSchedules(String isEnable,String order_by,String order){
        return scheduleRepository.selectSchedules(isEnable,order_by,order);
    }


}
