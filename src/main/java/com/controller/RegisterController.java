package com.controller;

import com.bean.*;
import com.service.CourseService;
import com.service.RegisterService;
import com.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private CourseService courseService;



    //返回某门课程的选课数据页面。
    @GetMapping("Teacher/register/{courseId}")
    public ModelAndView registerPage(@PathVariable("courseId")int courseId){
        ModelAndView mav=new ModelAndView("register");
        mav.addObject("courseId",courseId);
        return mav;
    }


    //增
    //学生选课，只需要传入课程id，后台通过session自动获取学生id
    @GetMapping("Register/newRegister/{courseId}")
    public String addNewRegister(HttpSession session, @PathVariable("courseId") int courseId) {
        //暂时新建一个学生，登陆做完后删除。
        User loginUser_pre = new User();
        loginUser_pre.setUserId(1);
        loginUser_pre.setUserCode("202004290326281402");
        session.setAttribute("loginUser", loginUser_pre);
        //删到这里。

        Schedule courseSchedule = scheduleService.getScheduleByCourseId(courseId);
        if (courseSchedule == null) {
            return "授课数据不存在！";
        } else if (registerService.addNewRegister(courseSchedule.getSch_teacherId(), ((User) session.getAttribute("loginUser")).getUserId(), courseId)) {
            return "选课成功！";
        }
        return "您已选了该课程！";
    }

    //删
    //学生调用，退选课程。
    @GetMapping("Register/removeRegister/{courseId}")
    public boolean removeRegister(HttpSession session, @PathVariable("courseId") int courseId) {
        //暂时新建一个学生，登陆做完后删除。
        User loginUser_pre = new User();
        loginUser_pre.setUserId(2);
        loginUser_pre.setUserCode("202004290326281402");
        session.setAttribute("loginUser", loginUser_pre);
        //删到这里。

        return registerService.removeRegister(((User) session.getAttribute("loginUser")).getUserId(), courseId);
    }

    //改
    //教师调用，打分。
    @GetMapping("Register/giveGrade/{courseId}/{studentId}/{grade}/{testScore}")
    public boolean giveGrade(@PathVariable("courseId") int courseId, @PathVariable("studentId") int studentId, @PathVariable("grade") float grade, @PathVariable("testScore") float testScore) {
        Course course = courseService.getCourseById(courseId);
        String[] gradePolicy = course.getCourseGradingPolicy().split("\\n");
        float finalScore = (float) ((Float.parseFloat(gradePolicy[0].substring(5, 7)) * grade + Float.parseFloat(gradePolicy[1].substring(5, 7)) * testScore) * 0.01);
        return registerService.giveGrade(courseId, studentId, grade, testScore, finalScore);
    }

    //查
    //根据课程id查询某门课程的选课数据，可以用来查所有选择某门课的学生。
    // page表示第几页，pageSize表示每页几条数据
    @GetMapping("Register/registerByCourseId/{courseId}/{page}/{pageSize}")
    public List<Register> getRegisterByCourseId(@PathVariable("courseId") int reg_courseId, @PathVariable("page") int page, @PathVariable("pageSize") int pageSize) {

        return registerService.getRegisterByCourseId(reg_courseId, page, pageSize);
//        List<Register> registers = registerService.getRegisterByCourseId(reg_courseId);
//        Map<String, Object> result = new HashMap<String, Object>();
//        result.put("code", 0);
//        result.put("msg", "查询成功");
//        result.put("count", registers.size());
//        result.put("data", registers);
//        return result;
    }

    //学生调用，查询当前登陆的学生的所有选课数据。无需参数，后台通过session自动获取学生id。
    //Ps:只能查询到启用的选课数据，假如这个学生选了课a，b，c，但是管理员软删掉了课程a，那么学生只能查到b，c
    // page表示第几页，pageSize表示每页几条数据
    @GetMapping("Register/myRegister")
    public List<Register> getMyRegister(HttpSession session) {
        //暂时新建一个学生，登陆做完后删除。
        User loginUser_pre = new User();
        loginUser_pre.setUserId(2);
        loginUser_pre.setUserCode("202004290326281402");
        session.setAttribute("loginUser", loginUser_pre);
        //删到这里。

        return registerService.getMyRegister(((User) session.getAttribute("loginUser")).getUserId());
    }

    //查询所有的选课数据。
    // order_by表示根据哪个字段查询
    // order表示正序还是倒序查询，order为0表示逆序，1表示正序
    // page表示第几页，pageSize表示每页几条数据
    // PS：两个order主要实现根据某个字段排序的功能
    @GetMapping("Register/registers/{order_by}/{order}/{page}/{pageSize}")
    public List<Register> getRegisters(@PathVariable("order_by") String order_by, @PathVariable("order") String order, @PathVariable("page") int page, @PathVariable("pageSize") int pageSize) {
        return registerService.getRegisters(order_by, order, page, pageSize);
    }




    //查询功能！！！
    //根据特定字段查询，查询范围：teacher，course，student
    @GetMapping("SearchRe/{param}/{value}")
    public List<Register> searchRegister(@PathVariable("param") String param,@PathVariable("value") String value){
        return registerService.getAll(param,value);
    }

}
