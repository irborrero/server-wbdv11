package com.example.springbootserver.repositories;


import com.example.springbootserver.models.Discussion;
import com.example.springbootserver.models.Topic;
import com.example.springbootserver.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiscussionRepository extends CrudRepository<Discussion, Integer> {

    @Query("SELECT discussion from Discussion discussion")
    List<Discussion> findAllDiscussions();

    @Query("SELECT discussion from Discussion discussion WHERE discussion.topic.id = :topicId")
    List<Discussion> findDiscussionsForTopic(@Param("topicId") Integer topicId);

    @Query("SELECT user from User user WHERE user.id IN " +
            "(SELECT discussion.user.id FROM Discussion discussion WHERE discussion.topic.id = :topicId)")
    List<User> findUsersForTopic(@Param("topicId") Integer topicId);
}
