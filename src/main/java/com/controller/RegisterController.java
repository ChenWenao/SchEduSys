package com.controller;

import com.bean.Register;
import com.bean.Schedule;
import com.bean.Teacher;
import com.bean.User;
import com.service.RegisterService;
import com.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @Autowired
    private ScheduleService scheduleService;

    //学生选课，只需要传入课程id，后台通过session自动获取学生id
    @RequestMapping("Register/newRegister/{courseId}")
    public String addNewRegister(HttpSession session, @PathVariable("courseId") int courseId){
        //暂时新建一个学生，登陆做完后删除。
        User loginUser_pre=new User();
        loginUser_pre.setUserId(1);
        loginUser_pre.setUserCode("201722111920129");
        session.setAttribute("loginUser",loginUser_pre);
        //删到这里。

        Schedule courseSchedule= scheduleService.getScheduleByCourseId(courseId);
        if(courseSchedule==null){
            return "授课数据不存在！";
        }
        else if(registerService.addNewRegister(courseSchedule.getSch_teacherId(), ((User)session.getAttribute("loginUser")).getUserId(), courseId)){
                return "选课成功！";
        }
        return "您已选了该课程！";
    }

    @RequestMapping("Register/removeRegister/{courseId}")
    public boolean removeRegister(HttpSession session,@PathVariable("courseId") int courseId){
        //暂时新建一个学生，登陆做完后删除。
        User loginUser_pre=new User();
        loginUser_pre.setUserId(1);
        loginUser_pre.setUserCode("201722111920129");
        session.setAttribute("loginUser",loginUser_pre);
        //删到这里。


    }

}
