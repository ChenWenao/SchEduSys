package com.bean;

public class Register {
    private int registerId;
    private int reg_scheduleId;
    private int reg_studentId;
    private int reg_courseId;
    private String isEnable;
    private float grade;
    private float testScore;
    private float finalScore;

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

    public int getReg_courseId() {
        return reg_courseId;
    }

    public void setReg_courseId(int reg_courseId) {
        this.reg_courseId = reg_courseId;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    public float getTestScore() {
        return testScore;
    }

    public void setTestScore(float testScore) {
        this.testScore = testScore;
    }

    public float getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(float finalScore) {
        this.finalScore = finalScore;
    }
}
