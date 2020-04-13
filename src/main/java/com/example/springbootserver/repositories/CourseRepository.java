package com.example.springbootserver.repositories;
import com.example.springbootserver.models.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.example.springbootserver.models.Course;

import java.util.List;

public interface CourseRepository extends CrudRepository <Course,Integer> {

    @Query("SELECT course from Course course")
    List<Course> findAllCourses();

    @Query("SELECT course FROM Course course WHERE course.id = :courseId")
    Course findCourseById(@Param("courseId") String courseId);
}

