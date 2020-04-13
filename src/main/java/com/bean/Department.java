package com.bean;

import java.sql.Date;
import java.sql.Time;

public class Department {
    private int departId;
    private String departName;
    private String isEnable;
    private Date departCreateTime;
    private String departDescription;

    public int getDepartId() {
        return departId;
    }

    public void setDepartId(int departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public Date getDepartCreateTime() {
        return departCreateTime;
    }

    public void setDepartCreateTime(Date departCreateTime) {
        this.departCreateTime = departCreateTime;
    }

    public String getDepartDescription() {
        return departDescription;
    }

    public void setDepartDescription(String departDescription) {
        this.departDescription = departDescription;
    }

}
