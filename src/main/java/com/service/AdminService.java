package com.service;

import com.bean.Admin;
import com.dao.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    //增
    public boolean addNewAdmin(Admin newAdmin) {
        return adminRepository.insertANewAdmin(newAdmin);
    }

    //删
    public boolean removeAdmin(int adminId) {
        return adminRepository.deleteAdmin(adminId);
    }

    //改
    public boolean dropAdmin(int adminId) {
        return adminRepository.dropAdmin(adminId);
    }

    public boolean restoreAdmin(int adminId) {
        return adminRepository.restoreAdmin(adminId);
    }

    //查
    public Admin getAdminById(int adminId) {
        return adminRepository.selectAdminById(adminId);
    }

    public Admin getAdminByCode(String adminCode) {
        return adminRepository.selectAdminByCode(adminCode);
    }

    public List<Admin> getAdmins(String isEnable, String order_by, String order,int page,int pageSize) {
        return adminRepository.selectAdmins(isEnable, order_by, order,page,pageSize);
    }

}