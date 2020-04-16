package com.example.springbootserver.controllers;

import com.example.springbootserver.models.Event;
import com.example.springbootserver.models.Topic;
import com.example.springbootserver.models.User;
import com.example.springbootserver.services.EventService;
import com.example.springbootserver.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class EventController {

    @Autowired
    EventService service;

    @GetMapping("/api/events")
    public List<Event> findAllEvents() {
        return service.findAllEvents();
    }

//    @PostMapping("/api/events/")
//    public void profile(HttpSession session, @RequestBody Event newEvent) {
//        User profile = (User)session.getAttribute("profile");
//        service.insertEvent(newEvent);
//        profile.getEvents().add(newEvent);
//    }

//
//    @GetMapping("/api/courses/{courseId}/topics")
//    public List<Topic> findTopicsForCourse(@PathVariable("courseId") String courseId) {
//        return service.findTopicsForCourse(courseId);
//    }

}
