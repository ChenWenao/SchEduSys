package com.service;

import com.bean.News;
import com.dao.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {
    @Autowired
    private NewsRepository newsRepository;
    //增
    public boolean addNewNews(String newsTitle,String newsURL){
        return newsRepository.insertANewNews(newsTitle,newsURL);
    }

    //删
    public boolean removeNews(int newsId){
        return newsRepository.deleteNews(newsId);
    }

    //改
    public boolean modifyNews(News modifyNews){
        return newsRepository.modifyNews(modifyNews);
    }

    public boolean restoreNews(int newsId){
        return newsRepository.restoreNews(newsId);
    }

    public boolean dropNews(int newsId){
        return newsRepository.dropNews(newsId);
    }

    //查
    public News getNewsById(int newsId){
        return newsRepository.selectNewsById(newsId);
    }

    public List<News> getNews(String isEnable,String order_by,String order){
        return newsRepository.selectNews(isEnable, order_by, order);
    }


}
