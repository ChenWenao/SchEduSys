package com.dao;

import com.bean.Topic;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicRowMapper implements RowMapper<Topic> {
    @Override
    public Topic mapRow(ResultSet resultSet, int i) throws SQLException {
        Topic topic = new Topic();
        topic.setTopicId(resultSet.getInt("topicId"));
        topic.setTopicName(resultSet.getString("topicName"));
        return topic;
    }
}
