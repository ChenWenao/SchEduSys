package com.controller;

import com.bean.Course;
import com.bean.Department;
import com.bean.Topic;
import com.service.CourseService;
import com.service.DepartService;
import com.service.TopicService;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
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
    @Autowired
    private DepartService departService;
    @Autowired
    private TopicService topicService;

    @RequestMapping("/SchEduSys/Course/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    //增
    //此处为新建课程，需要课程字段：name，topicName，departName，Period，Credit，StartTime
    //Logo（courseImg），Level，Type。
    @RequestMapping("/SchEduSys/Course/newCourse")
    public String addNewCourse(@RequestParam("courseImg") MultipartFile courseImg, @ModelAttribute(value = "newCourse") Course newCourse) {
        String msg = "";
        //设置课程类型以及学院的id。
        if (departService.getDepartmentByName(newCourse.getCourseDepartName()) == null) {
            msg = "学院不存在，请先新建学院";
            return msg;
        } else if (topicService.getTopicByName(newCourse.getCourseTopicName()) == null) {
            topicService.addNewTopic(newCourse.getCourseTopicName());
            msg = "课程类型不存在，已自动新建课程类型！";
        }

        newCourse.setCourseTopicId(topicService.getTopicByName(newCourse.getCourseTopicName()).getTopicId());
        newCourse.setCourseDepartId(departService.getDepartmentByName(newCourse.getCourseDepartName()).getDepartId());

        //判断是否传入图片。
        if (courseImg.isEmpty()) {
            msg = "请上传图片！";
            return msg;
        }
        //设置imgName。
        String imgName = System.currentTimeMillis() + courseImg.getOriginalFilename();
        //获取课程图片存储文件夹，若不存在，就创建文件夹。
        String fileDirPath = new String("src/main/resources/img/courseImg");
        File fileDir = new File(fileDirPath);
        if (!fileDir.exists()) {
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
            //设置课程图片Logo。
            newCourse.setCourseLogo(newFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //至此，输入的有：name，topicName，departName，Period，Credit，StartTime，Level，Type
        //获取的有：topicId，departId，Logo
        //共11个字段。
        //插入课程。
        courseService.addNewCourse(newCourse);
        msg += "课程新建成功！";
        return msg;
    }

    //删
    @RequestMapping("/SchEduSys/Course/removeCourse/{courseId}")
    public boolean removeCourse(@PathVariable("courseId") int courseId) {
        Course course_drop=courseService.getCourseById(courseId);
        if (course_drop != null) {
            File file = new File(course_drop.getCourseLogo());//根据指定的文件名创建File对象
            if ( file.exists()&&file.isFile() ){
                return file.delete()&&courseService.removeCourse(courseId);
            }
        }
        return false;
    }

    //改
    @RequestMapping("/SchEduSys/Course/dropCourse/{courseId}")
    public boolean dropCourse(@PathVariable("courseId") int courseId) {
        if (courseService.getCourseById(courseId) != null)
            return courseService.dropCourse(courseId);
        return false;
    }

    @RequestMapping("/SchEduSys/Course/restoreCourse/{courseId}")
    public boolean restoreCourse(@PathVariable("courseId") int courseId) {
        if (courseService.getCourseById(courseId) != null)
            return courseService.restoreCourse(courseId);
        return false;
    }


    //查
    @RequestMapping("/SchEduSys/Course/CourseById/{courseId}")
    public Course getCourseById(@PathVariable("courseId") int courseId){
        return courseService.getCourseById(courseId);
    }


}

