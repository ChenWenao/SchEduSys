package com.service;

import com.bean.Topic;
import com.dao.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    //增
    public boolean addNewTopic(String topicName) {
        return topicRepository.insertANewTopic(topicName);
    }

    //删
    public boolean removeTopic(String topicName) {
        return topicRepository.deleteTopic(topicName);
    }

    //改
    public boolean modifyTopic(String oldTopicName, String newTopicName) {
        return topicRepository.updateTopic(oldTopicName, newTopicName);
    }

    //查
    public Topic getTopicByName(String topicName) {
        return topicRepository.selectTopicByName(topicName);
    }

    public Topic getTopicById(int topicId) {
        return topicRepository.selectTopicById(topicId);
    }

    public List<Topic> getAllTopics(int page, int pageSize) {
        return topicRepository.selectAllTopics(page, pageSize);
    }
}
