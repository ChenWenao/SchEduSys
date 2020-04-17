package com.controller;


import com.bean.Admin;
import com.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController{
    @Autowired
    private AdminService adminService;

    //增
    //传入字段：userCode,userIdCard,userRealName,userIdentity       PS:isEnable默认是T，启用状态，密码默认123456，让用户自己修改。
    //adminCode(其实就是userCode),adminGender,adminPoliticeStatus,adminPhoneNumber,adminRealName(其实就是user的userRealName),
    //adminIdCard(其实就是user的userIdCard),adminNote
    //Ps:adminCreateTime不用管，数据库默认插入新建用户的时间。






    //查
    @RequestMapping("/SchEduSys/Admin/adminById/{adminId}")
    public Admin getAdminById(@PathVariable("adminId") int adminId){
        return adminService.getAdminById(adminId);
    }
}