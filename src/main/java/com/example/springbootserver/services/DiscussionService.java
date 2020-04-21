package com.example.springbootserver.services;

import com.example.springbootserver.models.Discussion;
import com.example.springbootserver.models.Topic;
import com.example.springbootserver.models.User;
import com.example.springbootserver.repositories.DiscussionRepository;
import com.example.springbootserver.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();

        newDiscussion.setDate(LocalDateTime.now());
        return discussionRepository.save(newDiscussion);
    }

    public List<User> findUsersForTopic(Integer topicId) {
        return discussionRepository.findUsersForTopic(topicId);
    }

    public List<Discussion> findDiscussionsForTopic(Integer topicId) {
        Topic topic = topicRepository.findTopicById(topicId);
        List<Discussion> discussions = topic.getDiscussions();
        for(Discussion discussion: discussions){
            discussion.getUser().setPassword("***");
        }
        return discussions;
    }
}
