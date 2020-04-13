package com.example.springbootserver.services;

import com.example.springbootserver.models.Course;
import com.example.springbootserver.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> findAllCourses() {
        return courseRepository.findAllCourses();
    }

    public Course findCourseById(String courseId) {
        return courseRepository.findCourseById(courseId);
    }

    public Course createCourse(Course newCourse) {
        return courseRepository.save(newCourse);
    }

    public int updateCourse(String courseId, Course updatedCourse) {
        if(updatedCourse == null)
            return 0;
        courseRepository.save(updatedCourse);
        return 1;
    }
}
