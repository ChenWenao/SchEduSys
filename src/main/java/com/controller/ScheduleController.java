package com.controller;

import com.bean.*;
import com.service.CourseService;
import com.service.RegisterService;
import com.service.ScheduleService;
import com.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private RegisterService registerService;


    //增

    //由管理员调用，安排授课数据。
    //传入的表单名为newSchedule
    //要求有如下字段：sch_courseId，sch_teacherId，selectStartTime，selectEndTime，scoreStartTime，scoreEndTime
    //所有时间若无设置，默认为当前时间
    @PostMapping("Schedule/newSchedule")
    public String addNewSchedule(@ModelAttribute(value = "newSchedule") Schedule newSchedule) {
        Course sch_course = courseService.getCourseById(newSchedule.getSch_courseId());
        Teacher sch_teacher = teacherService.getTeacherById(newSchedule.getSch_teacherId());
        if (sch_course != null && sch_teacher != null)
            if ("F".equals(sch_course.getHaveTeacher()) && scheduleService.addNewSchedule(newSchedule)) {
                if ("必修".equals(sch_course.getCourseType()))
                    registerService.addCompulsory(sch_course, sch_teacher);//若为必修，插入所有该学院的学生的选课数据。
                return "课程分配教师完成！";
            } else
                return "课程已有教师！";
        return "课程或教师不存在！";
    }


    //删
    //硬删除，由管理员调用，删除课程的教师分配。同时删除选择这个授课的选课相关信息。并将课程设置为没有老师的状态。
    @PostMapping("Schedule/removeSchedule")
    public boolean removeSchedule(@RequestBody List<Integer> sch_courseIds) {
        for (int sch_courseId : sch_courseIds) {
            if (!scheduleService.removeSchedule(sch_courseId)) {
                return false;
            }
        }
        return true;
    }

    //改
    //软删除，由管理员调用，下架一门课程的授课信息，同时会下架该授课的学生选课的数据。
    @PostMapping("Schedule/dropSchedule")
    public boolean dropSchedule(@RequestBody List<Integer> sch_courseIds) {
        for (int sch_courseId : sch_courseIds) {
            if (!scheduleService.dropSchedule(sch_courseId)) {
                return false;
            }
        }
        return true;
    }

    //上架（恢复）授课信息。
    @PostMapping("Schedule/restoreSchedule")
    public boolean restoreSchedule(@RequestBody List<Integer> sch_courseIds) {
        for (int sch_courseId : sch_courseIds) {
            if (!scheduleService.restoreSchedule(sch_courseId)) {
                return false;
            }
        }
        return true;
    }

    //发布分数。
    @GetMapping("Schedule/publishScore/{courseId}")
    public boolean publishScore(@PathVariable("courseId") int courseId) {
        return scheduleService.publishScore(courseId);
    }

    //停止发布分数
    @GetMapping("Schedule/publishScoreOff/{courseId}")
    public boolean publishScoreOff(@PathVariable("courseId") int courseId) {
        return scheduleService.publishScoreOff(courseId);
    }

    //查，同时会查出该授课计划的课程信息以及教师信息

    //查询单个数据。

    //查询某个课程的授课数据，单条，一个课程只能有一条授课信息（即一个课程只能有一个老师），学生查询选课详细信息时用
    @GetMapping("Schedule/scheduleByCourseId/{courseId}")
    public Schedule getScheduleByCourseId(@PathVariable("courseId") int courseId) {
        return scheduleService.getScheduleByCourseId(courseId);
    }

    //查询多条数据。

    //查询当前老师的授课数据，多条（可能一个老师教n门课）
    //giveScore为on表示查询自己教的课程中，已经可以打分的课程
    //为off表示查询还不可打分的课程。
    //all表示查询自己教的所有课程
    // page表示第几页，pageSize表示每页几条数据
    @GetMapping("Schedule/mySchedule/{giveScore}/{page}/{pageSize}")
    public List<Schedule> getScheduleByTeacherId(HttpSession session, @PathVariable("giveScore") String giveScore, @PathVariable("page") int page, @PathVariable("pageSize") int pageSize) {

        //暂时建立一个session，登陆做完后删除
        User loginUser_pre = new User();
        loginUser_pre.setUserId(1);
        loginUser_pre.setUserCode("202004290326282401");
        session.setAttribute("loginUser", loginUser_pre);
        //删到这里。

        return scheduleService.getScheduleByTeacherId(((User) session.getAttribute("loginUser")).getUserCode(), giveScore, page, pageSize);
    }

    //查询所有的授课数据。
    // isEnable表示是否启用，on表示查询启用的授课数据，off表示查询未启用的授课数据，all表示查询所有
    // order_by表示根据哪个字段查询
    // order表示正序还是倒序查询，order为0表示逆序，1表示正序
    // page表示第几页，pageSize表示每页几条数据
    // PS：两个order主要实现根据某个字段排序的功能
    @GetMapping("Schedule/schedules/{isEnable}/{order_by}/{order}/{page}/{pageSize}")
    public List<Schedule> getSchedules(@PathVariable("isEnable") String isEnable, @PathVariable("order_by") String order_by, @PathVariable("order") String order, @PathVariable("page") int page, @PathVariable("pageSize") int pageSize) {
        return scheduleService.getSchedules(isEnable, order_by, order, page, pageSize);
    }

    //学生调用，查询所有可以选的课程。（查询已经开放选课的课程）
    // page表示第几页，pageSize表示每页几条数据
    @GetMapping("Schedule/schedulesOn/{order_by}/{order}/{page}/{pageSize}")
    public List<Schedule> getSelectSchedules(HttpSession session, @PathVariable("order_by") String order_by, @PathVariable("order") String order, @PathVariable("page") int page, @PathVariable("pageSize") int pageSize) {
        //暂时新建一个学生，登陆做完后删除。
        User loginUser_pre = new User();
        loginUser_pre.setUserId(2);
        loginUser_pre.setUserCode("202004290326281402");
        session.setAttribute("loginUser", loginUser_pre);
        //删到这里。

        return scheduleService.getOnSchedules(((User) session.getAttribute("loginUser")).getUserId(), order_by, order, page, pageSize);
    }


    //查询功能！！！
    //根据特定字段查询，查询范围：teacher，course，
    @GetMapping("SearchSc/{param}/{value}")
    public List<Schedule> searchSchedule(@PathVariable("param") String param, @PathVariable("value") String value) {
        return scheduleService.getAll(param, value);
    }
}
