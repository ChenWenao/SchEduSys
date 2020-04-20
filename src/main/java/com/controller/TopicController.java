package com.controller;

import com.bean.Topic;
import com.service.TopicService;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopicController {
    @Autowired
    private TopicService topicService;

    //增

    //新建课程类型
    @RequestMapping("Topic/newTopic/{topicName}")
    public boolean addNewTopic(@PathVariable("topicName") String topicName) {
        return topicService.addNewTopic(topicName);
    }

    //删

    //删除课程类型，同时会删除课程
    @RequestMapping("Topic/removeTopic/{topicName}")
    public boolean removeTopic(@PathVariable("topicName") String topicName) {
        if (topicService.getTopicByName(topicName) != null)
            return topicService.removeTopic(topicName);
        return false;
    }

    //改

    //修改课程类型
    @RequestMapping("Topic/modifyTopic/{oldTopicName}/{newTopicName}")
    public boolean modifyTopic(@PathVariable("oldTopicName") String oldTopicName, @PathVariable("newTopicName") String newTopicName) {
        if (topicService.getTopicByName(oldTopicName) != null) {
            return topicService.modifyTopic(oldTopicName, newTopicName);
        }
        return false;
    }

    //查
    @RequestMapping("Topic/topicByName/{topicName}")
    public Topic getTopicByName(@PathVariable("topicName") String topicName) {
        return topicService.getTopicByName(topicName);
    }

    @RequestMapping("Topic/topicById/{topicId}")
    public Topic getTopicById(@PathVariable("topicId") int topicId) {
        return topicService.getTopicById(topicId);
    }

    @RequestMapping("Topic/allTopics")
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

}
