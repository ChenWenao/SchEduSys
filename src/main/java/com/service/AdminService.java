package com.service;


import com.bean.Admin;
import com.dao.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService{
    @Autowired
    private AdminRepository adminRepository;

    //查
    public Admin getAdminById(int adminId) {
        return adminRepository.selectAdminById(adminId);
    }



}