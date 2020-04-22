package com.bean;

import java.sql.Date;

public class Register {
    private int reg_studentId;
    private int reg_teacherId;
    private int reg_courseId;
    private String isEnable;
    private float grade;
    private float testScore;
    private float finalScore;

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

    //Student
    private String studentCode;
    private int studentDepartId;
    private String studentDepartName;
    private String studentGender;
    private String studentNativePlace;
    private String studentPoliticsStatus;
    private String studentPhoneNumber;
    private String studentRealName;
    private String studentIdCard;
    private Date studentEntryTime;
    private String studentNote;

    public int getReg_studentId() {
        return reg_studentId;
    }

    public void setReg_studentId(int reg_studentId) {
        this.reg_studentId = reg_studentId;
    }

    public int getReg_teacherId() {
        return reg_teacherId;
    }

    public void setReg_teacherId(int reg_teacherId) {
        this.reg_teacherId = reg_teacherId;
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

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public int getStudentDepartId() {
        return studentDepartId;
    }

    public void setStudentDepartId(int studentDepartId) {
        this.studentDepartId = studentDepartId;
    }

    public String getStudentDepartName() {
        return studentDepartName;
    }

    public void setStudentDepartName(String studentDepartName) {
        this.studentDepartName = studentDepartName;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentNativePlace() {
        return studentNativePlace;
    }

    public void setStudentNativePlace(String studentNativePlace) {
        this.studentNativePlace = studentNativePlace;
    }

    public String getStudentPoliticsStatus() {
        return studentPoliticsStatus;
    }

    public void setStudentPoliticsStatus(String studentPoliticsStatus) {
        this.studentPoliticsStatus = studentPoliticsStatus;
    }

    public String getStudentPhoneNumber() {
        return studentPhoneNumber;
    }

    public void setStudentPhoneNumber(String studentPhoneNumber) {
        this.studentPhoneNumber = studentPhoneNumber;
    }

    public String getStudentRealName() {
        return studentRealName;
    }

    public void setStudentRealName(String studentRealName) {
        this.studentRealName = studentRealName;
    }

    public String getStudentIdCard() {
        return studentIdCard;
    }

    public void setStudentIdCard(String studentIdCard) {
        this.studentIdCard = studentIdCard;
    }

    public Date getStudentEntryTime() {
        return studentEntryTime;
    }

    public void setStudentEntryTime(Date studentEntryTime) {
        this.studentEntryTime = studentEntryTime;
    }

    public String getStudentNote() {
        return studentNote;
    }

    public void setStudentNote(String studentNote) {
        this.studentNote = studentNote;
    }
}
