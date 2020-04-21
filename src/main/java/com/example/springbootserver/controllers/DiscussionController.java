package com.example.springbootserver.controllers;

import com.example.springbootserver.models.Discussion;
import com.example.springbootserver.models.Topic;
import com.example.springbootserver.models.User;
import com.example.springbootserver.services.DiscussionService;
import com.example.springbootserver.services.TopicService;
import com.example.springbootserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://project-react-client-t11.herokuapp.com", allowCredentials = "true")
public class DiscussionController {

    @Autowired
    DiscussionService discussionService;

    @Autowired
    UserService userService;

    @GetMapping("/api/discussions")
    public List<Discussion> findAllDiscussions() {
        return discussionService.findAllDiscussions();
    }

    @PostMapping("/api/topics/{topicId}/discussions")
    public Discussion createDiscussion(@PathVariable("topicId") Integer topicId, @RequestBody Discussion newDiscussion, HttpSession session) {
        User profile = (User)session.getAttribute("profile");
        User user = userService.findUserById(profile.getId());
        return discussionService.createDiscussion(newDiscussion, topicId, user);
    }

    @GetMapping("/api/topics/{topicId}/users")
    public List<User> findUsersForTopic(@PathVariable("topicId") Integer topicId) {
        List<User> users = discussionService.findUsersForTopic(topicId);
        for (User user : users) {
            user.setPassword("***");
        }
        return users;
    }

    @GetMapping("/api/topics/{topicId}/discussions")
    public List<Discussion> findDiscussionsForTopic(@PathVariable("topicId") Integer topicId) {
        return discussionService.findDiscussionsForTopic(topicId);
    }

}
