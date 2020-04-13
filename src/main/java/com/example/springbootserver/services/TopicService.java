package com.example.springbootserver.services;

import com.example.springbootserver.models.Topic;
import com.example.springbootserver.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    public List<Topic> findAllTopics() {
        return topicRepository.findAllTopics();
    }

    public Topic createTopic(Topic newTopic) {
        return topicRepository.save(newTopic);
    }

}
