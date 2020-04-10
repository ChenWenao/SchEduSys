package com.bean;

public class Schedule {
    private int scheduleId;
    private int sch_courseId;
    private int sch_teacherId;
    private String isEnable;
    private String publishScore;

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getSch_courseId() {
        return sch_courseId;
    }

    public void setSch_courseId(int sch_courseId) {
        this.sch_courseId = sch_courseId;
    }

    public int getSch_teacherId() {
        return sch_teacherId;
    }

    public void setSch_teacherId(int sch_teacherId) {
        this.sch_teacherId = sch_teacherId;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getPublishScore() {
        return publishScore;
    }

    public void setPublishScore(String publishScore) {
        this.publishScore = publishScore;
    }

}
