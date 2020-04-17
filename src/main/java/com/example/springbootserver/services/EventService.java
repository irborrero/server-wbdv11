package com.example.springbootserver.services;

import com.example.springbootserver.models.Course;
import com.example.springbootserver.models.Event;
import com.example.springbootserver.models.Topic;
import com.example.springbootserver.models.User;
import com.example.springbootserver.repositories.CourseRepository;
import com.example.springbootserver.repositories.EventRepository;
import com.example.springbootserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    EventRepository repository;

    @Autowired
    CourseRepository courseRepository;

    public List<Event> findAllEvents() {
        return repository.findAllEvents();
    }

    public void insertEvent(Event event) { repository.save(event);}

    public Event createEvent(Event newEvent, String courseId) {
        Course course = courseRepository.findCourseById(courseId);
        newEvent.setCourse(course);
        return repository.save(newEvent);
    }

    public List<Event> findEventsForCourse(String courseId) {
        Course course = courseRepository.findCourseById(courseId);
        return course.getEvents();
    }
}
