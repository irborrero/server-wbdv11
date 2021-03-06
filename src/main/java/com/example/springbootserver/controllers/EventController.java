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
@CrossOrigin(origins = "https://project-react-client-t11.herokuapp.com", allowCredentials = "true")
public class EventController {

    @Autowired
    EventService service;

    @GetMapping("/api/events")
    public List<Event> findAllEvents() {
        return service.findAllEvents();
    }

    @PostMapping("/api/courses/{courseId}/events")
    public Event createEvent(@PathVariable("courseId") String courseId, @RequestBody Event newEvent) {
        return service.createEvent(newEvent, courseId);
    }

    @GetMapping("/api/courses/{courseId}/events")
    public List<Event> findEventsForCourse(@PathVariable("courseId") String courseId) {
        return service.findEventsForCourse(courseId);
    }

    @GetMapping("/api/events/{eventId}")
    public Event findEventsById(@PathVariable("eventId") String eventId) {
        return service.findEventById(eventId);
    }

    @DeleteMapping("/api/events/{eventId}")
    public void deleteEvent(@PathVariable("eventId") String eventId) {
        service.deleteEvent(eventId);
    }


}
