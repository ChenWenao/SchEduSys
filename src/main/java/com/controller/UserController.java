package com.controller;


import com.bean.Register;
import com.bean.Student;
import com.bean.Teacher;
import com.bean.User;
import com.service.StudentService;
import com.service.TeacherService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
public class UserController{
    @Autowired
    private UserService userService;
    private StudentService studentService;
    private TeacherService teacherService;

    //查
    @GetMapping("User/userById/{userId}")
    public User getUserById(@PathVariable("userId") int userId){
        return userService.getUserById(userId);
    }

    @GetMapping("User/userByCode/{userCode}")
    public User getUserByCode(@PathVariable("userCode") String userCode){
        return userService.getUserByCode(userCode);
    }

    //登录
    @PostMapping("User/login/{userCode}/{userPassword}")
    public User login_in(HttpServletRequest req, HttpServletResponse resp,HttpSession session, @PathVariable("userCode") String userCode, @PathVariable("userPassword") String userPassword) throws ServletException, IOException {
        if(!"".equals(userCode) && !"".equals(userPassword)) {
            User user = userService.login(userCode, userPassword);
            if (user != null) {
                if (user.getUserIdentity() == "学生") {
                    Student loginUser = studentService.getStudentByCode(userCode);
                    session.setAttribute("loginUser", loginUser);
                    req.getRequestDispatcher("Student.jsp").forward(req, resp);
                }
                else if (user.getUserIdentity() == "教师") {
                    Teacher loginUser = teacherService.getTeacherByCode(userCode);
                    session.setAttribute("loginUser", loginUser);
                    req.getRequestDispatcher("Teacher.jsp").forward(req, resp);
                }
                else System.out.println("账号或密码错误！");
            }
        }
        else System.out.println("账号或密码为空！");
        return null;
    }
}