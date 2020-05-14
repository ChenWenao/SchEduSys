package com.controller;


import com.bean.*;
import com.service.AdminService;
import com.service.StudentService;
import com.service.TeacherService;
import com.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
    @GetMapping("/User/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login.html");
        return mav;
    }

    //返回找回密码验证页面
    @GetMapping("User/authentication")
    public ModelAndView authentication() {
        ModelAndView mav = new ModelAndView("authentication");
        return mav;
    }

    @GetMapping("/User/resetPassword")
    public ModelAndView getPage(){
        ModelAndView mav = new ModelAndView("resetpassword");
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
    @PostMapping("/User/login")
    public ModelAndView login(HttpSession session, @ModelAttribute(value = "loginUser") User loginUser) throws NoSuchAlgorithmException {
        ModelAndView mav = new ModelAndView();//新建要返回的页面。
        //加密密码
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(loginUser.getUserPassword().getBytes());
        String password_MD5 = new BigInteger(1, md5.digest()).toString(16);
        loginUser.setUserPassword(password_MD5);
        //验证登录
        User user_find = userService.login(loginUser.getUserCode(), loginUser.getUserPassword());
        if (user_find != null) {
            if ("学生".equals(user_find.getUserIdentity())) {
                Student loginStudent = studentService.getStudentByCode(user_find.getUserCode());
                user_find.setUserId(loginStudent.getStudentId());
                session.setAttribute("loginUser", user_find);
               User user =  (User)session.getAttribute("loginUser");
               System.out.println(user.getUserRealName());
               System.out.println(user.getUserIdentity());
                mav.setViewName("redirect:/Student/student");   //设置学生主页。
            } else if ("教师".equals(user_find.getUserIdentity())) {
                Teacher loginTeacher = teacherService.getTeacherByCode(user_find.getUserCode());
                user_find.setUserId(loginTeacher.getTeacherId());
                session.setAttribute("loginUser", user_find);
                mav.setViewName("redirect:/Teacher/teacher");   //设置教师主页。
            } else if ("管理员".equals(user_find.getUserIdentity())) {
                Admin loginAdmin = adminService.getAdminByCode(loginUser.getUserCode());
                user_find.setUserId(loginAdmin.getAdminId());
                session.setAttribute("loginUser", user_find);
                mav.setViewName("redirect:/Admin/manage");   //设置管理员主页。
            }
        } else {
            mav.setViewName("redirect:/User/login");
        }
        return mav;
    }

    //找回密码验证，验证要修改密码的人的身份，验证成功会跳转到修改密码的页面，失败则会返回一个新的找回密码验证页面。
    //如有需要，可以改为返回boolean，前端来做页面跳转
    //为了信息安全，采用post方法
    //传入的表单名称为resetUser
    //表单需要包含字段：userRealName,userIdCard,userIdentity
    @PostMapping("/User/resetPasswordCheck")
    public boolean resetPasswordCheck(HttpSession session, @ModelAttribute(value = "resetUser") User resetUser) {
        User user_find = userService.resetPasswordCheck(resetUser.getUserIdCard(), resetUser.getUserRealName(), resetUser.getUserIdentity());
        if (user_find != null) {
            session.setAttribute("resetUser", user_find);
            return true;
        }
      System.out.println(resetUser.getUserRealName());
        return false;
    }



    //重置密码
    //Post方法，传入字段名称为：newPassword
    @PostMapping("User/resetPassword")
    public boolean resetPassword(HttpSession session, @ModelAttribute(value = "newPassword") String newPassword) throws NoSuchAlgorithmException {
        ModelAndView mav = new ModelAndView();
        User resetUser = (User) session.getAttribute("resetUser");//拿到前面验证时添加的用户。

        session.removeAttribute("resetUser");//拿完用户后，关闭session，节省系统资源。

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(newPassword.getBytes());
        String newPassword_MD5 = new BigInteger(1, md5.digest()).toString(16);
        resetUser.setUserPassword(newPassword_MD5);

        if (userService.modifyPassword(resetUser)) {
            return true;
        } else {
            return false;
        }
    }

}