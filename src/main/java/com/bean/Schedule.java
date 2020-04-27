package com.bean;


import java.util.Date;

public class Schedule {
    private int sch_courseId;
    private int sch_teacherId;
    private String isEnable;
    private String publishScore;
    private Date selectStartTime;
    private Date selectEndTime;
    private Date scoreStartTime;
    private Date scoreEndTime;

    //以下为附带字段：
    //Course
    private String courseName;
    private int courseTopicId;
    private String courseTopicName;
    private int courseDepartId;
    private String courseDepartName;
    private int coursePeriod;
    private float courseCredit;
    private Date courseStartTime;
    private String courseLogo;
    private int courseLevel;
    private String courseType;
    private String courseDescription;
    private String courseFAQ;
    private String courseGradingPolicy;
    private String courseRequirement;

    //Teacher
    private String teacherCode;
    private int teacherDepartId;
    private String teacherDepartName;
    private String teacherGender;
    private String teacherNativePlace;
    private String teacherPoliticsStatus;
    private String teacherDescription;
    private String teacherPhoneNumber;
    private String teacherRealName;
    private String teacherIdCard;
    private Date teacherEntryTime;


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


    public Date getSelectStartTime() {
        return selectStartTime;
    }

    public void setSelectStartTime(Date selectStartTime) {
        this.selectStartTime = selectStartTime;
    }

    public Date getSelectEndTime() {
        return selectEndTime;
    }

    public void setSelectEndTime(Date selectEndTime) {
        this.selectEndTime = selectEndTime;
    }

    public Date getScoreStartTime() {
        return scoreStartTime;
    }

    public void setScoreStartTime(Date scoreStartTime) {
        this.scoreStartTime = scoreStartTime;
    }

    public Date getScoreEndTime() {
        return scoreEndTime;
    }

    public void setScoreEndTime(Date scoreEndTime) {
        this.scoreEndTime = scoreEndTime;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseTopicId() {
        return courseTopicId;
    }

    public void setCourseTopicId(int courseTopicId) {
        this.courseTopicId = courseTopicId;
    }

    public String getCourseTopicName() {
        return courseTopicName;
    }

    public void setCourseTopicName(String courseTopicName) {
        this.courseTopicName = courseTopicName;
    }

    public int getCourseDepartId() {
        return courseDepartId;
    }

    public void setCourseDepartId(int courseDepartId) {
        this.courseDepartId = courseDepartId;
    }

    public String getCourseDepartName() {
        return courseDepartName;
    }

    public void setCourseDepartName(String courseDepartName) {
        this.courseDepartName = courseDepartName;
    }

    public int getCoursePeriod() {
        return coursePeriod;
    }

    public void setCoursePeriod(int coursePeriod) {
        this.coursePeriod = coursePeriod;
    }

    public float getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(float courseCredit) {
        this.courseCredit = courseCredit;
    }

    public Date getCourseStartTime() {
        return courseStartTime;
    }

    public void setCourseStartTime(Date courseStartTime) {
        this.courseStartTime = courseStartTime;
    }

    public String getCourseLogo() {
        return courseLogo;
    }

    public void setCourseLogo(String courseLogo) {
        this.courseLogo = courseLogo;
    }

    public int getCourseLevel() {
        return courseLevel;
    }

    public void setCourseLevel(int courseLevel) {
        this.courseLevel = courseLevel;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseFAQ() {
        return courseFAQ;
    }

    public void setCourseFAQ(String courseFAQ) {
        this.courseFAQ = courseFAQ;
    }

    public String getCourseGradingPolicy() {
        return courseGradingPolicy;
    }

    public void setCourseGradingPolicy(String courseGradingPolicy) {
        this.courseGradingPolicy = courseGradingPolicy;
    }

    public String getCourseRequirement() {
        return courseRequirement;
    }

    public void setCourseRequirement(String courseRequirement) {
        this.courseRequirement = courseRequirement;
    }

    public String getTeacherCode() {
        return teacherCode;
    }

    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }

    public int getTeacherDepartId() {
        return teacherDepartId;
    }

    public void setTeacherDepartId(int teacherDepartId) {
        this.teacherDepartId = teacherDepartId;
    }

    public String getTeacherDepartName() {
        return teacherDepartName;
    }

    public void setTeacherDepartName(String teacherDepartName) {
        this.teacherDepartName = teacherDepartName;
    }

    public String getTeacherGender() {
        return teacherGender;
    }

    public void setTeacherGender(String teacherGender) {
        this.teacherGender = teacherGender;
    }

    public String getTeacherNativePlace() {
        return teacherNativePlace;
    }

    public void setTeacherNativePlace(String teacherNativePlace) {
        this.teacherNativePlace = teacherNativePlace;
    }

    public String getTeacherPoliticsStatus() {
        return teacherPoliticsStatus;
    }

    public void setTeacherPoliticsStatus(String teacherPoliticsStatus) {
        this.teacherPoliticsStatus = teacherPoliticsStatus;
    }

    public String getTeacherDescription() {
        return teacherDescription;
    }

    public void setTeacherDescription(String teacherDescription) {
        this.teacherDescription = teacherDescription;
    }

    public String getTeacherPhoneNumber() {
        return teacherPhoneNumber;
    }

    public void setTeacherPhoneNumber(String teacherPhoneNumber) {
        this.teacherPhoneNumber = teacherPhoneNumber;
    }

    public String getTeacherRealName() {
        return teacherRealName;
    }

    public void setTeacherRealName(String teacherRealName) {
        this.teacherRealName = teacherRealName;
    }

    public String getTeacherIdCard() {
        return teacherIdCard;
    }

    public void setTeacherIdCard(String teacherIdCard) {
        this.teacherIdCard = teacherIdCard;
    }

    public Date getTeacherEntryTime() {
        return teacherEntryTime;
    }

    public void setTeacherEntryTime(Date teacherEntryTime) {
        this.teacherEntryTime = teacherEntryTime;
    }
}
