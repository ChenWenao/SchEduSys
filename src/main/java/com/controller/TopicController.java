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
    TopicService topicService;

    //增
    @RequestMapping("/SchEduSys/Topic/newTopic/{topicName}")
    public boolean addNewTopic(@PathVariable("topicName") String topicName){
        return topicService.addNewTopic(topicName);
    }

    //删
    @RequestMapping("/SchEduSys/Topic/removeTopic/{topicName}")
    public boolean removeTopic(@PathVariable("topicName") String topicName){
        return topicService.removeTopic(topicName);
    }

    //改
    @RequestMapping("/SchEduSys/Topic/modifyTopic/{oldTopicName}/{newTopicName}")
    public boolean modifyTopic(@PathVariable("oldTopicName") String oldTopicName,@PathVariable("newTopicName") String newTopicName){
        return topicService.modifyTopic(oldTopicName,newTopicName);
    }

    //查
    @RequestMapping("/SchEduSys/Topic/topicByName/{topicName}")
    public Topic getTopicByName(@PathVariable("topicName") String topicName){
        return topicService.getTopicByName(topicName);
    }

    @RequestMapping("/SchEduSys/Topic/topicById/{topicId}")
    public Topic getTopicById(@PathVariable("topicId") int topicId){
        return topicService.getTopicById(topicId);
    }

    @RequestMapping("/SchEduSys/Topic/allTopics")
    public List<Topic> getAllTopics(){
        return topicService.getAllTopics();
    }

}
