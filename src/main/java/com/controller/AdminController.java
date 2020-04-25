package com.controller;


import com.bean.Admin;
import com.bean.User;
import com.service.AdminService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class AdminController{
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    //增
    //传入字段：userIdCard,userRealName       PS:isEnable默认是T，启用状态，密码默认123456，用户自己修改。
    //adminNativePlace,adminGender,adminPoliticeStatus,adminPhoneNumber,adminNote
    //Ps:adminCreateTime不用管，数据库默认插入新建用户的时间。
    @PostMapping("Admin/newAdmin")
    public boolean addNewAdmin(@ModelAttribute(value = "newAdmin") Admin newAdmin, @ModelAttribute(value="newUser") User newUser) {
        //将user的数据同步到admin
        newAdmin.setAdminIdCard(newUser.getUserIdCard());
        newAdmin.setAdminRealName(newUser.getUserRealName());
        newUser.setUserIdentity("管理员");
        //生成管理员号
        SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMdd");
        String code=formatter.format(new Date(System.currentTimeMillis()));//code开头为日期
        code+=newAdmin.getAdminIdCard().substring(newAdmin.getAdminIdCard().length()-8);//code接下来为身份证后八位
        if("男".equals(newAdmin.getAdminGender()))
            code+="0";
        else if("女".equals(newAdmin.getAdminGender()))
            code+="1";

        //管理员尾数为0，教师尾数为1，学生尾数为2
        if("管理员".equals(newUser.getUserIdentity()))
            code+="0";
        else if ("教师".equals(newUser.getUserIdentity()))
            code+="1";
        else
            code+="2";
        newUser.setUserCode(code);
        newAdmin.setAdminCode(code);
        if(userService.addNewUser(newUser)) {
            if (adminService.addNewAdmin(newAdmin))
                return true;
            else
                userService.removeUserByCode(newUser.getUserCode());
        }
        return false;
    }

    //删
    //真删
    @PostMapping("Admin/removeAdmin")
    public boolean removeAdmin(@RequestBody List<Integer> adminIds) {
        for (int adminId : adminIds) {
            if (!adminService.removeAdmin(adminId)) {
                return false;
            }
        }
        return true;
    }
    //改
    //软删
    @PostMapping("Admin/dropAdmin")
    public boolean dropAdmin(@RequestBody List<Integer> adminIds) {
        for (int adminId : adminIds) {
            if (!adminService.dropAdmin(adminId)) {
                return false;
            }
        }
        return true;
    }
    //恢复
    @PostMapping("Admin/restoreAdmin")
    public boolean restoreAdmin(@RequestBody List<Integer> adminIds) {
        for (int adminId : adminIds) {
            if (!adminService.restoreAdmin(adminId)) {
                return false;
            }
        }
        return true;
    }

    //查
    @RequestMapping("Admin/adminById/{adminId}")
    public Admin getAdminById(@PathVariable("adminId") int adminId){
        return adminService.getAdminById(adminId);
    }

    @RequestMapping("Admin/adminByCode/{adminCode}")
    public Admin getAdminByCode(@PathVariable("adminCode") String adminCode){
        return adminService.getAdminByCode(adminCode);
    }
}