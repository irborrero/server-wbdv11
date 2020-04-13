package com.example.springbootserver.controllers;

import ch.qos.logback.core.pattern.FormatInfo;
import com.example.springbootserver.models.Course;
import com.example.springbootserver.models.Topic;
import com.example.springbootserver.services.CourseService;
import com.example.springbootserver.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class TopicController {

    @Autowired
    TopicService service;

    @GetMapping("/api/topics")
    public List<Topic> findAllTopics() {
        return service.findAllTopics();
    }

    @PostMapping("/api/topics")
    public Topic createTopic(@RequestBody Topic newTopic) {
        return service.createTopic(newTopic);
    }

    /*

    @GetMapping("/api/courses/{courseId}/topics")
    public List<Topic> findTopicsForCourse(@PathVariable("courseId") String courseId) {
        return service.findCourseById(courseId);
    }
   */

}
