package com.example.springbootserver.repositories;


import com.example.springbootserver.models.Course;
import com.example.springbootserver.models.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer>{

    @Query("SELECT topic from Topic topic")
    List<Topic> findAllTopics();

    @Query("SELECT topic FROM Topic topic WHERE topic.id = :topicId")
    Topic findTopicById(@Param("topicId") Integer topicId);
}