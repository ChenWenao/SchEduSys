package com.dao;

import com.bean.Department;
import com.bean.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsRepository {
    @Autowired
    private JdbcTemplate template;
    private NewsRowMapper newsRowMapper = new NewsRowMapper();

    //增
    public boolean insertANewNews(String newsTitle, String newsURL) {
        try {
            template.update("insert into News(newsTitle,newsURL) values (?,?)", newsTitle, newsURL);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }


    //删
    public boolean deleteNews(int newsId) {
        try {
            template.update("delete from News where newsId=?", newsId);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //改
    public boolean modifyNews(News modifyNews){
        try {
            template.update("update News set newsTitle=?,newsURL=? where newsId=?",modifyNews.getNewsTitle(),modifyNews.getNewsURL(),modifyNews.getNewsId());
            return true;
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    public boolean restoreNews(int newsId) {
        try {
            template.update("update News set isEnable='T' where newsId=?", newsId);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean dropNews(int newsId) {
        try {
            template.update("update News set isEnable='F' where newsId=?", newsId);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }


    //查
    public News selectNewsById(int newsId) {
        try {
            List<News> news = template.query("select * from News where newsId=?", newsRowMapper, newsId);
            return news.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<News> selectNews(String isEnable, String order_by, String order,int page,int pageSize) {
        try {
            String sql = "select * from News ";
            if ("Release".equals(isEnable))
                sql += "where isEnable='T' order by ";
            else if ("unRelease".equals(isEnable))
                sql += "where isEnable='F' order by ";
            else
                sql += "order by ";
            sql += order_by;
            if ("0".equals(order))
                sql += " desc";
            sql += " limit " + (page - 1) * pageSize + "," + pageSize;
            List<News> news = template.query(sql, newsRowMapper);
            return news;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
