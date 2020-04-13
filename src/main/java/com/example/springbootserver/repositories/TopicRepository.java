package com.example.springbootserver.repositories;


import com.example.springbootserver.models.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TopicRepository extends CrudRepository<Topic, Integer>{

    @Query("SELECT topic from Topic topic")
    List<Topic> findAllTopics();


}