package com.service;


import com.bean.Admin;
import com.bean.Course;
import com.dao.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService{
    @Autowired
    private AdminRepository adminRepository;

    //增
    public boolean addNewAdmin(Admin newAdmin) {
        return adminRepository.insertANewAdmin(newAdmin);
    }

    //查
    public Admin getAdminById(int adminId) {
        return adminRepository.selectAdminById(adminId);
    }



}