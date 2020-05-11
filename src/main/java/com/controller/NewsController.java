package com.controller;

import com.bean.Course;
import com.bean.News;
import com.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class NewsController {
    @Autowired
    private NewsService newsService;

    //增
    //新建新闻，传入的为文件，名字为 newNewsFile
    //同时还需传入一个字段，newsTitle。
    @PostMapping("News/newNews")
    public boolean newNews(@RequestParam("newNewsFile") MultipartFile newNewsFile, @ModelAttribute(value = "newsTitle") String newsTitle) {
        String newsURL = null;
        //判断是否传入图片。
        if (newNewsFile.isEmpty()) {
            return false;
        }
        String newsName = System.currentTimeMillis() + newNewsFile.getOriginalFilename();
        String fileDirPath = "src/main/resources/static/files/news";
        File fileDir = new File(fileDirPath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        try {
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + newsName);
            newNewsFile.transferTo(newFile);
            newsURL = newFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newsService.addNewNews(newsTitle, newsURL);
    }

    //删
    //真删
    @PostMapping("News/removeNews")
    public boolean removeNews(@RequestBody List<Integer> newsIds) {
        try {
            for (int newsId : newsIds) {
                News news_drop = newsService.getNewsById(newsId);
                if (news_drop != null) {
                    File file = new File(news_drop.getNewsURL());
                    if (file.exists() && file.isFile()) {
                        file.delete();
                        newsService.removeNews(newsId);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //改
    //发布新闻，可一次发布多条新闻。
    @PostMapping("News/ReleaseNews")
    public boolean restoreNews(@RequestBody List<Integer> newsIds) {
        for (int newsId : newsIds) {
            if (newsService.getNewsById(newsId) == null || !newsService.restoreNews(newsId)) {
                return false;
            }
        }
        return true;
    }

    //取消发布新闻（下架新闻）。
    @PostMapping("News/unReleaseNews")
    public boolean dropNews(@RequestBody List<Integer> newsIds) {
        for (int newsId : newsIds) {
            if (newsService.getNewsById(newsId) == null || !newsService.dropNews(newsId)) {
                return false;
            }
        }
        return true;
    }

    //修改新闻
    //传入的是新闻对象和新闻文件（即：用新的新闻文件替代掉这个新闻id的旧文件）
    //新闻对象名为 newsInfo ，需要包含newsId和newsTitle。
    //新闻文件名为：modifyNewsFile
    @PostMapping("News/modifyNews")
    public boolean modifyNews(@RequestParam("modifyNewsFile") MultipartFile modifyNewsFile, @ModelAttribute(value = "newsInfo") News newsInfo) {
        //先从数据库拿到要修改的新闻数据。
        News news_find = newsService.getNewsById(newsInfo.getNewsId());
        //删掉旧的新闻文件。
        File file = new File(news_find.getNewsURL());
        if (file.exists() && file.isFile()) {
            file.delete();
        }
        //把新的文件放进去
        if (modifyNewsFile.isEmpty()) {
            return false;
        }
        String newsName = System.currentTimeMillis() + modifyNewsFile.getOriginalFilename();
        String fileDirPath = "src/main/resources/files/news";
        File fileDir = new File(fileDirPath);
        //创建文件夹
        if (!fileDir.exists()) {
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        try {
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + newsName);
            modifyNewsFile.transferTo(newFile);
            news_find.setNewsURL(newFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        news_find.setNewsTitle(newsInfo.getNewsTitle());
        return newsService.modifyNews(news_find);
    }

    //查
    //根据id查询单个新闻。
    @GetMapping("News/newsById/{newsId}")
    public News getNewsById(@PathVariable("newsId") int newsId) {
        return newsService.getNewsById(newsId);
    }

    //查询新闻列表
    //isEnable为Release表示查询已经发布的新闻，
    //isEnable为unRelease表示暂未发布的新闻，
    //isEnable为All表示查询所有新闻。
    // order_by表示根据哪个字段查询，
    // order表示正序还是倒序查询，order为0表示逆序，1表示正序
    // page表示第几页，pageSize表示每页几条数据
    @GetMapping("News/news/{isEnable}/{order_by}/{order}/{page}/{pageSize}")
    public List<News> getNews(@PathVariable("isEnable") String isEnable, @PathVariable("order_by") String order_by, @PathVariable("order") String order, @PathVariable("page") int page, @PathVariable("pageSize") int pageSize) {
        return newsService.getNews(isEnable, order_by, order, page, pageSize);
    }


}
