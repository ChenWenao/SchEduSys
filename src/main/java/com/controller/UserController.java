package com.controller;


import com.bean.*;
import com.service.AdminService;
import com.service.StudentService;
import com.service.TeacherService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private AdminService adminService;


    //返回登陆页面
    @GetMapping("User/login")
    public ModelAndView login(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("login");
        return mav;
    }




    //查
    @GetMapping("User/userById/{userId}")
    public User getUserById(@PathVariable("userId") int userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("User/userByCode/{userCode}")
    public User getUserByCode(@PathVariable("userCode") String userCode) {
        return userService.getUserByCode(userCode);
    }

    //登录
    //为了信息安全，采用post方法
    //传入的表单名称为loginUser
    //表单需要包含字段：userCode,userPassword
    @PostMapping("User/login")
    public ModelAndView login_in(HttpSession session, @ModelAttribute(value = "loginUser") User loginUser) throws NoSuchAlgorithmException {
        ModelAndView mav=new ModelAndView();//新建要返回的页面。
        //加密密码
        MessageDigest md5=MessageDigest.getInstance("MD5");
        md5.update(loginUser.getUserPassword().getBytes());
        String password_MD5=new BigInteger(1,md5.digest()).toString(16);
        loginUser.setUserPassword(password_MD5);
        //验证登录
        User user_find = userService.login(loginUser.getUserCode(), loginUser.getUserPassword());
        if (user_find != null) {
            if ("学生".equals(user_find.getUserIdentity())) {
                Student loginStudent = studentService.getStudentByCode(user_find.getUserCode());
                user_find.setUserId(loginStudent.getStudentId());
                session.setAttribute("loginUser", user_find);
                mav.setViewName("student");   //设置学生主页。
            } else if ("教师".equals(user_find.getUserIdentity())) {
                Teacher loginTeacher = teacherService.getTeacherByCode(user_find.getUserCode());
                user_find.setUserId(loginTeacher.getTeacherId());
                session.setAttribute("loginUser", user_find);
                mav.setViewName("teacher");   //设置教师主页。
            } else if ("管理员".equals(user_find.getUserIdentity())) {
                Admin loginAdmin=adminService.getAdminByCode(loginUser.getUserCode());
                user_find.setUserId(loginAdmin.getAdminId());
                session.setAttribute("loginUser", user_find);
                mav.setViewName("manage");   //设置管理员主页。
            }
            return mav;
        } else
            return null;
    }
}