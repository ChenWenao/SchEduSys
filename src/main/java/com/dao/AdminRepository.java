package com.dao;

import com.bean.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepository{
    @Autowired
    private JdbcTemplate template;
    private AdminRowMapper adminRowMapper=new AdminRowMapper();

    //增
    public boolean insertANewAdmin(Admin newAdmin) {
        try {
            template.update("insert into Admin(adminCode,adminNativePlace,adminGender,adminPoliticsStatus,adminPhoneNumber,adminRealName,adminIdCard,adminNote) values(?,?,?,?,?,?,?,?)"
                    , newAdmin.getAdminCode()
                    , newAdmin.getAdminNativePlace()
                    , newAdmin.getAdminGender()
                    , newAdmin.getAdminPoliticsStatus()
                    , newAdmin.getAdminPhoneNumber()
                    , newAdmin.getAdminRealName()
                    , newAdmin.getAdminIdCard()
                    , newAdmin.getAdminNote());
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    //删
    public boolean deleteAdmin(int adminId) {
        try {
            List<Admin> admins=template.query("select * from Admin where adminId=?",adminRowMapper,adminId);
            template.update("delete from Admin where adminId=?",adminId);
            template.update("delete from User where userCode=?",admins.get(0).getAdminCode());
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //改
    public boolean dropAdmin(int adminId){
        try {
            //删除User
            List<Admin> admins=template.query("select * from Admin where adminId=?",adminRowMapper,adminId);
            template.update("update User set isEnable='F' where userCode =?", admins.get(0).getAdminCode());
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean restoreAdmin(int adminId){
        try {
            //恢复User
            List<Admin> admins=template.query("select * from Admin where adminId=?",adminRowMapper,adminId);
            template.update("update User set isEnable='T' where userCode =?", admins.get(0).getAdminCode());
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //查
    public Admin selectAdminById(int adminId) {
        try {
            List<Admin> admins = template.query("select * from Admin where adminId =?", adminRowMapper, adminId);
            return admins.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Admin selectAdminByCode(String adminCode) {
        try {
            List<Admin> admins = template.query("select * from Admin where adminCode =?", adminRowMapper, adminCode);
            return admins.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}