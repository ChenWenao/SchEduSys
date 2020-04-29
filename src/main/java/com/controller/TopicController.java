package com.controller;

import com.bean.Topic;
import com.service.TopicService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TopicController {
    @Autowired
    private TopicService topicService;

    //增

    //新建课程类型
    @GetMapping("Topic/newTopic/{topicName}")
    public boolean addNewTopic(@PathVariable("topicName") String topicName) {
        return topicService.addNewTopic(topicName);
    }

    //删

    //删除课程类型，同时会删除课程
    @PostMapping("Topic/removeTopic")
    public boolean removeTopic(@RequestBody List<String> topicNames) {
        for (String topicName : topicNames) {
            if (topicService.getTopicByName(topicName) == null || !topicService.removeTopic(topicName))
                return false;
        }
        return true;
    }

    //改

    //修改课程类型
    @GetMapping("Topic/modifyTopic/{oldTopicName}/{newTopicName}")
    public boolean modifyTopic(@PathVariable("oldTopicName") String oldTopicName, @PathVariable("newTopicName") String newTopicName) {
        if (topicService.getTopicByName(oldTopicName) != null) {
            return topicService.modifyTopic(oldTopicName, newTopicName);
        }
        return false;
    }

    //查
    @GetMapping("Topic/topicByName/{topicName}")
    public Topic getTopicByName(@PathVariable("topicName") String topicName) {
        return topicService.getTopicByName(topicName);
    }

    @GetMapping("Topic/topicById/{topicId}")
    public Topic getTopicById(@PathVariable("topicId") int topicId) {
        return topicService.getTopicById(topicId);
    }

    // page表示第几页，pageSize表示每页几条数据
    @GetMapping("Topic/allTopics/{page}/{pageSize}")
    public List<Topic> getAllTopics(@PathVariable("page")int page,@PathVariable("pageSize")int pageSize) {
        return topicService.getAllTopics(page,pageSize);
    }

}
