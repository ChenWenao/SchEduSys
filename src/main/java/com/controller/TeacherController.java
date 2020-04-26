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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class TeacherController{
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DepartService departService;
    @Autowired
    private UserService userService;


    //暂时用这个url来返回teacher页面，在登陆做完后，可以由登陆来返回teacher页面，到那时候，这个接口可以删掉。
    @GetMapping("Teacher/teacher")
    public ModelAndView teacherHome(){
        ModelAndView mav=new ModelAndView("teacher");
        return mav;
    }



    //增
    //传入字段：userIdCard,userRealName       PS:isEnable默认是T，启用状态，密码默认123456，用户自己修改。
    //teacherDepartName,teacherGender,teacherNativePlace,teacherPoliticsStatus,teacherPhoneNumber,teacherDescription
    //Ps:studentEntryTime不用管，数据库默认插入新建用户的时间。
    @PostMapping("Teacher/newTeacher")
    public String addNewTeacher(@ModelAttribute(value = "newTeacher") Teacher newTeacher,@ModelAttribute(value="newUser") User newUser) {
        //将user的数据同步到admin
        newTeacher.setTeacherIdCard(newUser.getUserIdCard());
        newTeacher.setTeacherRealName(newUser.getUserRealName());
        newUser.setUserIdentity("教师");
        //获取学院id
        Department department_find=departService.getDepartmentByName(newTeacher.getTeacherDepartName());
        if(department_find==null)
            return "学院不存在！";
        newTeacher.setTeacherDepartId(department_find.getDepartId());

        //生成学号
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
        String code=formatter.format(new Date(System.currentTimeMillis()));//code开头为日期
        code+=newTeacher.getTeacherIdCard().substring(newTeacher.getTeacherIdCard().length()-8);//code接下来为身份证后八位
        if("男".equals(newTeacher.getTeacherGender()))
            code+="0";
        else if("女".equals(newTeacher.getTeacherGender()))
            code+="1";

        //管理员尾数为0，教师尾数为1，学生尾数为2
        if("管理员".equals(newUser.getUserIdentity()))
            code+="0";
        else if ("教师".equals(newUser.getUserIdentity()))
            code+="1";
        else
            code+="2";
        newUser.setUserCode(code);
        newTeacher.setTeacherCode(code);
        if(userService.addNewUser(newUser)) {
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
    //也就是说，只能修改以上字段
    @PostMapping("Teacher/modifyTeacher")
    public String modifyTeacher(@ModelAttribute(value = "modifyTeacher") Teacher modifyTeacher) {
        Teacher student_find = teacherService.getTeacherById(modifyTeacher.getTeacherId());
        if (student_find == null)
            return "教师不存在！";//要修改的student不存在。
        Department department_find=departService.getDepartmentByName(modifyTeacher.getTeacherDepartName());
        if(department_find==null){
            return "要修改的学院不存在！";
        }
        modifyTeacher.setTeacherDepartId(department_find.getDepartId());
        if(teacherService.modifyTeacher(modifyTeacher))
            return "修改成功！";
        return "修改失败！";
    }

    //查
    @RequestMapping("Teacher/teacherById/{teacherId}")
    public Teacher getTeacherById(@PathVariable("teacherId") int teacherId){
        System.out.println("true");
        return teacherService.getTeacherById(teacherId);
    }


    @RequestMapping("Teacher/teacherByCode/{teacherCode}")
    public Teacher getTeacherByCode(@PathVariable("teacherCode") String teacherCode){
        return teacherService.getTeacherByCode(teacherCode);
    }




}