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

    //查
    @RequestMapping("/SchEduSys/Admin/adminById/{adminId}")
    public Admin getAdminById(@PathVariable("adminId") int adminId){
        return adminService.getAdminById(adminId);
    }
}