package com.bean;

import java.util.Date;

public class News {

    private int newsId;
    private String newsTitle;
    private String isEnable;
    private String newsURL;
    private Date newsReleaseTime;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getNewsURL() {
        return newsURL;
    }

    public void setNewsURL(String newsURL) {
        this.newsURL = newsURL;
    }

    public Date getNewsReleaseTime() {
        return newsReleaseTime;
    }

    public void setNewsReleaseTime(Date newsReleaseTime) {
        this.newsReleaseTime = newsReleaseTime;
    }


}
