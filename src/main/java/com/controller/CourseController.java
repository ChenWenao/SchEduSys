package com.controller;

import com.bean.Course;
import com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.ws.Response;
import java.io.File;
import java.io.IOException;
import java.security.Timestamp;
import java.util.Date;
import java.util.Random;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @RequestMapping("/SchEduSys/Course/index")
    public ModelAndView index(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    //此处为新建课程，需要课程字段：name，topicName，departName，Period，Credit，StartTime
    //Logo（courseImg），Level，Type。
    @RequestMapping("/SchEduSys/Course/newCourse")
    public boolean addNewCourse(@RequestParam("courseImg") MultipartFile courseImg,Course newCourse){
        //判断是否传入图片。
        if (courseImg.isEmpty()) {
            System.out.println("没有上传图片！");
            return false;
        }
        //设置imgName。
        String imgName = System.currentTimeMillis()+courseImg.getOriginalFilename();
        //获取课程图片存储文件夹，若不存在，就创建文件夹。
        String fileDirPath = new String("src/main/resources/img/courseImg");
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            // 递归生成文件夹
            fileDir.mkdirs();
        }
        try {
            // 构建真实的文件路径
            File newFile = new File(fileDir.getAbsolutePath() + File.separator + imgName);
            //输出文件路径。
            //System.out.println(newFile.getAbsolutePath());
            // 上传图片到 -》 “绝对路径”
            courseImg.transferTo(newFile);
            //System.out.println("上传成功！");
            newCourse.setCourseLogo(newFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }











        return true;
    }

}

