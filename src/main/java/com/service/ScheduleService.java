package com.service;

import com.bean.Schedule;
import com.dao.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    //增
    public boolean addNewSchedule(Schedule newSchedule) {
        return scheduleRepository.insertANewSchedule(newSchedule);
    }

    //删
    public boolean removeSchedule(int sch_courseId) {
        return scheduleRepository.deleteSchedule(sch_courseId);
    }

    //改
    public boolean dropSchedule(int sch_courseId) {
        return scheduleRepository.dropSchedule(sch_courseId);
    }

    public boolean restoreSchedule(int sch_courseId) {
        return scheduleRepository.restoreSchedule(sch_courseId);
    }

    public boolean publishScore(int courseId) {
        return scheduleRepository.publishScore(courseId);
    }

    public boolean publishScoreOff(int courseId) {
        return scheduleRepository.publishScoreOff(courseId);
    }

    //查
    public Schedule getScheduleByCourseId(int courseId) {
        return scheduleRepository.selectScheduleByCourseId(courseId);
    }

    public List<Schedule> getScheduleByTeacherId(String teacherCode, String giveScore,int page,int pageSize) {
        return scheduleRepository.selectScheduleByTeacherId(teacherCode, giveScore,page,pageSize);
    }

    public List<Schedule> getSchedules(String isEnable, String order_by, String order,int page,int pageSize) {
        return scheduleRepository.selectSchedules(isEnable, order_by, order,page, pageSize);
    }

    public List<Schedule> getOnSchedules(String order_by, String order,int page,int pageSize) {
        return scheduleRepository.selectOnSchedules(order_by, order,page,pageSize);
    }
}
