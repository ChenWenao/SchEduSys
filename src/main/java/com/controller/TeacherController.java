package com.controller;


import com.bean.Department;
import com.bean.Teacher;
import com.bean.User;
import com.service.DepartService;
import com.service.TeacherService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DepartService departService;
    @Autowired
    private UserService userService;


    //暂时用这个url来返回teacher页面，在登陆做完后，可以由登陆来返回teacher页面，到那时候，这个接口可以删掉。
    @GetMapping("Teacher/teacher")
    public ModelAndView teacherHome(HttpSession session) {
        ModelAndView mav = new ModelAndView("teacher");
        Teacher teacher = teacherService.getTeacherById(((User)session.getAttribute("loginUser")).getUserId());
        mav.addObject("Teacher",teacher);
        return mav;
    }

    //暂时用这个url来返回teacher的courseList，……
    @GetMapping("Teacher/courseList")
    public ModelAndView courseListPage(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("courseList");
        return  mav;
    }
    //暂时用这个url来返回teacher的examList，……
    @GetMapping("Teacher/examList")
    public ModelAndView examListPage(){
        ModelAndView mav=new ModelAndView("examList");
        return mav;
    }

    //暂时用这个url来返回teacher的editInfo，……
    @GetMapping("Teacher/editInfo")
    public  ModelAndView editProfile(){
        ModelAndView mav=new ModelAndView("editInfo");
        return  mav;
    }
    //暂时用这个url来返回teacher的修改课程信息页面
    @GetMapping("Teacher/editCourse/{courseId}")
    public ModelAndView editCourse(@PathVariable("courseId")int courseId){
        ModelAndView mav=new ModelAndView("editCourse");
        mav.addObject("courseId",courseId);
        return mav;
    }



    //增
    //传入字段：userIdCard,userRealName       PS:isEnable默认是T，启用状态，密码默认123456，用户自己修改。
    //teacherDepartName,teacherGender,teacherNativePlace,teacherPoliticsStatus,teacherPhoneNumber,teacherDescription
    //Ps:studentEntryTime不用管，数据库默认插入新建用户的时间。
    @PostMapping("Teacher/newTeacher")
    public String addNewTeacher(@ModelAttribute(value = "newTeacher") Teacher newTeacher, @ModelAttribute(value = "newUser") User newUser) {
        //将user的数据同步到admin
        newTeacher.setTeacherIdCard(newUser.getUserIdCard());
        newTeacher.setTeacherRealName(newUser.getUserRealName());
        newUser.setUserIdentity("教师");
        //获取学院id
        Department department_find = departService.getDepartmentByName(newTeacher.getTeacherDepartName());
        if (department_find == null)
            return "学院不存在！";
        newTeacher.setTeacherDepartId(department_find.getDepartId());

        //生成学号
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String code = formatter.format(new Date(System.currentTimeMillis()));//code开头为日期
        code += newTeacher.getTeacherIdCard().substring(newTeacher.getTeacherIdCard().length() - 8);//code接下来为身份证后八位
        if ("男".equals(newTeacher.getTeacherGender()))
            code += "0";
        else if ("女".equals(newTeacher.getTeacherGender()))
            code += "1";

        //管理员尾数为0，教师尾数为1，学生尾数为2
        if ("管理员".equals(newUser.getUserIdentity()))
            code += "0";
        else if ("教师".equals(newUser.getUserIdentity()))
            code += "1";
        else
            code += "2";
        newUser.setUserCode(code);
        newTeacher.setTeacherCode(code);
        if (userService.addNewUser(newUser)) {
            if (teacherService.addNewTeacher(newTeacher))
                return "教师创建成功！";
            else
                userService.removeUserByCode(newUser.getUserCode());
        }
        return "用户创建失败！";
    }

    //删
    @PostMapping("Teacher/removeTeacher")
    public boolean removeTeacher(@RequestBody List<Integer> teacherIds) {
        for (int teacherId : teacherIds) {
            if (!teacherService.removeTeacher(teacherId)) {
                return false;
            }
        }
        return true;
    }

    //改
    //注销教师
    @PostMapping("Teacher/dropTeacher")
    public boolean dropTeacher(@RequestBody List<Integer> teacherIds) {
        for (int teacherId : teacherIds) {
            if (teacherService.getTeacherById(teacherId) == null || !teacherService.dropTeacher(teacherId)) {
                return false;
            }
        }
        return true;
    }

    //恢复教师
    @PostMapping("Teacher/restoreTeacher")
    public boolean restoreTeacher(@RequestBody List<Integer> teacherIds) {
        for (int teacherId : teacherIds) {
            if (teacherService.getTeacherById(teacherId) == null || !teacherService.restoreTeacher(teacherId)) {
                return false;
            }
        }
        return true;
    }

    //修改教师信息
    //传入的modifyTeacher表单，需要包含以下字段：teacherId，teacherDepartName，teacherGender，teacherNativePlace，teacherPoliticsStatus
    //，teacherPhoneNumber，teacherRealName
    //也就是说，只能修改以上字段(teacherId不能修改)
    @PostMapping("Teacher/modifyTeacher")
    public String modifyTeacher(@ModelAttribute(value = "modifyTeacher") Teacher modifyTeacher) {
        Teacher teacher_find = teacherService.getTeacherById(modifyTeacher.getTeacherId());
        if (teacher_find == null)
            return "教师不存在！";//要修改的teacher不存在。
        Department department_find = departService.getDepartmentByName(modifyTeacher.getTeacherDepartName());
        if (department_find == null) {
            return "要修改的学院不存在！";
        }
        modifyTeacher.setTeacherDepartId(department_find.getDepartId());
        if (teacherService.modifyTeacher(modifyTeacher))
            return "修改成功！";
        return "修改失败！";
    }

    //查
    @GetMapping("Teacher/teacherById/{teacherId}")
    public Teacher getTeacherById(@PathVariable("teacherId") int teacherId) {
        return teacherService.getTeacherById(teacherId);
    }

    @GetMapping("Teacher/teacherByCode/{teacherCode}")
    public Teacher getTeacherByCode(@PathVariable("teacherCode") String teacherCode) {
        return teacherService.getTeacherByCode(teacherCode);
    }

    //批量查询
    //查询所有教师
    // isEnable表示是否启用，on表示查询启用的教师，off表示查询未启用的教师，all表示查询所有
    // order_by表示根据哪个字段查询
    // order表示正序还是倒序查询，order为0表示逆序，1表示正序
    // page表示第几页，pageSize表示每页几条数据
    @GetMapping("Teacher/teachers/{isEnable}/{order_by}/{order}/{page}/{pageSize}")
    public List<Teacher> getTeachers(@PathVariable("isEnable") String isEnable, @PathVariable("order_by") String order_by, @PathVariable("order") String order, @PathVariable("page") int page, @PathVariable("pageSize") int pageSize) {
        return teacherService.getTeachers(isEnable, order_by, order, page, pageSize);
    }


}