package com.example.springbootserver.controllers;

import ch.qos.logback.core.pattern.FormatInfo;
import com.example.springbootserver.models.Course;
import com.example.springbootserver.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CourseController {

    @Autowired
    CourseService service;

    @GetMapping("/api/courses")
    public List<Course> findAllCourses() {
        return service.findAllCourses();
    }


    @GetMapping("/api/courses/{courseId}")
    public Course findCourseById(@PathVariable("courseId") String courseId) {
        return service.findCourseById(courseId);
    }

    @PutMapping("/api/courses/{courseId}")
    public int updateWidget(@PathVariable("courseId") String courseId, @RequestBody Course updatedCourse) {
        return service.updateCourse(courseId, updatedCourse);
    }


    @PostMapping("/api/courses")
    public Course createCourse(@RequestBody Course newCourse) {
        return service.createCourse(newCourse);
    }

}
