package com.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class Admin {
    private int adminId;
    private String adminCode;
    private String adminNativePlace;
    private String adminGender;
    private String adminPoliticsStatus;
    private String adminPhoneNumber;
    private String adminRealName;
    private String adminIdCard;
    private Date adminCreateTime;
    private String adminNote;

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public String getAdminNativePlace() {
        return adminNativePlace;
    }

    public void setAdminNativePlace(String adminNativePlace) {
        this.adminNativePlace = adminNativePlace;
    }

    public String getAdminGender() {
        return adminGender;
    }

    public void setAdminGender(String adminGender) {
        this.adminGender = adminGender;
    }

    public String getAdminPoliticsStatus() {
        return adminPoliticsStatus;
    }

    public void setAdminPoliticsStatus(String adminPoliticsStatus) {
        this.adminPoliticsStatus = adminPoliticsStatus;
    }

    public String getAdminPhoneNumber() {
        return adminPhoneNumber;
    }

    public void setAdminPhoneNumber(String adminPhoneNumber) {
        this.adminPhoneNumber = adminPhoneNumber;
    }

    public String getAdminRealName() {
        return adminRealName;
    }

    public void setAdminRealName(String adminRealName) {
        this.adminRealName = adminRealName;
    }

    public String getAdminIdCard() {
        return adminIdCard;
    }

    public void setAdminIdCard(String adminIdCard) {
        this.adminIdCard = adminIdCard;
    }

    public Date getAdminCreateTime() {
        return adminCreateTime;
    }

    public void setAdminCreateTime(Date adminCreateTime) {
        this.adminCreateTime = adminCreateTime;
    }

    public String getAdminNote() {
        return adminNote;
    }

    public void setAdminNote(String adminNote) {
        this.adminNote = adminNote;
    }
}