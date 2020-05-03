package com.dao;

import com.bean.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TopicRepository {

    @Autowired
    private JdbcTemplate template;
    private TopicRowMapper topicRowMapper = new TopicRowMapper();

    //增
    public boolean insertANewTopic(String topicName) {
        try {
            template.update("insert into Topic(topicName) value(?)", topicName);
            return true;
        } catch (Exception e) {
            System.out.println(e);

        }
        return false;
    }


    //删
    public boolean deleteTopic(String topicName) {
        try {
            //删除course表的数据。
            template.update("delete from Course where courseTopicName=?", topicName);
            //删除topic表的数据。
            template.update("delete from Topic where topicName=?", topicName);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //改
    public boolean updateTopic(String oldTopicName, String newTopicName) {
        try {
            //更改course表。
            template.update("update Course set courseTopicName=? where courseTopicName=?", newTopicName, oldTopicName);
            //更改topic表。
            template.update("update Topic set topicName=? where topicName=?", newTopicName, oldTopicName);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }


    //查
    public Topic selectTopicByName(String topicName) {
        try {
            List<Topic> topics = template.query("select * from Topic where topicName = ?", topicRowMapper, topicName);
            return topics.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public Topic selectTopicById(int topicId) {
        try {
            List<Topic> topics = template.query("select * from Topic where topicId = ?", topicRowMapper, topicId);
            return topics.get(0);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Topic> selectAllTopics(int page, int pageSize) {
        try {
            List<Topic> topics = template.query("select * from Topic limit ?,?", topicRowMapper, (page - 1) * pageSize, pageSize);
            return topics;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
