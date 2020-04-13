package com.example.springbootserver.services;

import com.example.springbootserver.models.Discussion;
import com.example.springbootserver.models.Topic;
import com.example.springbootserver.models.User;
import com.example.springbootserver.repositories.DiscussionRepository;
import com.example.springbootserver.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DiscussionService {

    @Autowired
    DiscussionRepository discussionRepository;

    @Autowired
    TopicRepository topicRepository;

    public List<Discussion> findAllDiscussions() {
        return discussionRepository.findAllDiscussions();
    }

    public Discussion createDiscussion(Discussion newDiscussion, Integer topicId, User profile) {
        Topic topic = topicRepository.findTopicById(topicId);
        newDiscussion.setTopic(topic);
        newDiscussion.setUser(profile);
        return discussionRepository.save(newDiscussion);
    }

    public List<Discussion> findDiscussionsForTopic(Integer topicId) {
        Topic topic = topicRepository.findTopicById(topicId);
        return topic.getDiscussions();
    }
}
