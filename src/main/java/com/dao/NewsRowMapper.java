package com.dao;

import com.bean.News;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class NewsRowMapper implements RowMapper<News> {
    @Override
    public News mapRow(ResultSet resultSet, int i) throws SQLException {
        News news=new News();
        news.setNewsId(resultSet.getInt("newsId"));
        news.setNewsTitle(resultSet.getString("newsTitle"));
        news.setIsEnable(resultSet.getString("isEnable"));
        news.setNewsURL(resultSet.getString("newsURL"));
        news.setNewsReleaseTime(resultSet.getDate("newsReleaseTime"));
        return news;
    }

}










