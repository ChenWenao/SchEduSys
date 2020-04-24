package com.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class Course {
    private int courseId;
    private String isEnable;
    private String haveTeacher;
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getHaveTeacher() {
        return haveTeacher;
    }

    public void setHaveTeacher(String haveTeacher) {
        this.haveTeacher = haveTeacher;
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

}
