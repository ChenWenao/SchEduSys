package com.controller;

import com.bean.Course;
import com.bean.Schedule;
import com.bean.Teacher;
import com.service.CourseService;
import com.service.SchduleService;
import com.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleController {
    @Autowired
    private SchduleService schduleService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    //增
    //由管理员调用，为一门课程分配一个教师。
    @RequestMapping("Schedule/newRegister/{courseId}/{teacherId}")
    public String addNewSchedule(@PathVariable("courseId") int sch_courseId, @PathVariable("teacherId") int sch_teacherId){
        Course sch_course=courseService.getCourseById(sch_courseId);
        Teacher sch_teacher=teacherService.getTeacherById(sch_teacherId);
        if(sch_course!=null&&sch_teacher!=null)
            if("F".equals(sch_course.getHaveTeacher())&&schduleService.addNewSchedule(sch_courseId,sch_teacherId))
                return "课程分配教师完成！";
            else
                return "课程已有教师！";
        return "课程或教师不存在！";
    }

    //删
    //硬删除，由管理员调用，删除课程的教师分配。同时删除选择这个授课的选课相关信息。并将课程设置为没有老师的状态。
    @PostMapping("Schedule/removeSchedule")
    public boolean removeSchedule(@RequestBody List<Integer> sch_courseIds){
        for (int sch_courseId:sch_courseIds) {
            if(!schduleService.removeSchedule(sch_courseId)){
                return false;
            }
        }
        return true;
    }

    //改
    //软删除，由管理员调用，下架一门课程的授课信息，同时会下架该授课的学生选课的数据。
    @PostMapping("Schedule/dropSchedule")
    public boolean dropSchedule(@RequestBody List<Integer> sch_courseIds){
        for (int sch_courseId:sch_courseIds) {
            if(!schduleService.dropSchedule(sch_courseId)){
                return false;
            }
        }
        return true;
    }

    //上架（恢复）授课信息。
    @PostMapping("Schedule/restoreSchedule")
    public boolean restoreSchedule(@RequestBody List<Integer> sch_courseIds){
        for (int sch_courseId:sch_courseIds) {
            if(!schduleService.restoreSchedule(sch_courseId)){
                return false;
            }
        }
        return true;
    }

    //发布分数。
    @RequestMapping("Schedule/publishScore/{courseId}")
    public boolean publishScore(@PathVariable("courseId") int courseId){
        return schduleService.publishScore(courseId);
    }

    //查，同时会查出该授课计划的课程信息以及教师信息

    //查询单个数据。
    //order_by代表根据哪个id查询，只可以为以下值：“scheduleId”，“courseId”，“teacherId”
    //order_value为id的值
    @RequestMapping("Schedule/scheduleById/{order_by}/{order_value}")
    public Schedule getScheduleById(@PathVariable("order_by") String order_by,@PathVariable("order_value")int order_value){
        return schduleService.getScheduleById(order_by,order_value);
    }

    //查询多条数据。
    // isEnable表示是否启用，on表示查询启用的授课数据，off表示查询未启用的授课数据，all表示查询所有
    // order_by表示根据哪个字段查询
    // order表示正序还是倒序查询，order为0表示逆序，1表示正序
    @RequestMapping("Schedule/schedules/{isEnable}/{order_by}/{order}")
    public List<Schedule> getSchedules(@PathVariable("isEnable") String isEnable,@PathVariable("order_by")String order_by,@PathVariable("order")String order){
        return schduleService.getSchedules(isEnable,order_by,order);
    }

}
