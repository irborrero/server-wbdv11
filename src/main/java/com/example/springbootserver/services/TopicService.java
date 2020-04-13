package com.example.springbootserver.services;

import com.example.springbootserver.models.Course;
import com.example.springbootserver.models.Topic;
import com.example.springbootserver.repositories.CourseRepository;
import com.example.springbootserver.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    CourseRepository courseRepository;

    public List<Topic> findAllTopics() {
        return topicRepository.findAllTopics();
    }

    public Topic createTopic(Topic newTopic, String courseId) {
        Course course = courseRepository.findCourseById(courseId);
        newTopic.setCourse(course);
        return topicRepository.save(newTopic);
    }

    public List<Topic> findTopicsForCourse(String courseId) {
        Course course = courseRepository.findCourseById(courseId);
        return course.getTopics();
    }
}
