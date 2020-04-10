package com.bean;

public class Register {
    private int registerId;
    private int reg_scheduleId;
    private int reg_studentId;
    private String isEnable;
    private float score;

    public int getRegisterId() {
        return registerId;
    }

    public void setRegisterId(int registerId) {
        this.registerId = registerId;
    }

    public int getReg_scheduleId() {
        return reg_scheduleId;
    }

    public void setReg_scheduleId(int reg_scheduleId) {
        this.reg_scheduleId = reg_scheduleId;
    }

    public int getReg_studentId() {
        return reg_studentId;
    }

    public void setReg_studentId(int reg_studentId) {
        this.reg_studentId = reg_studentId;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }
}
