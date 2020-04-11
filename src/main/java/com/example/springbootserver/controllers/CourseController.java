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
 /*
package com.example.myapp.controllers;
        import com.example.myapp.models.Widget;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        import com.example.myapp.services.WidgetService;

        import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class WidgetController {

    @Autowired
    WidgetService service;

    @PostMapping("/api/topics/{tid}/widgets")
    public Widget createWidget(@PathVariable("tid") int tid, @RequestBody Widget newWidget) {
        return service.createWidget(tid, newWidget);
    }

    @GetMapping("/api/topics/{tid}/widgets")
    public List<Widget> findWidgetsForTopic(@PathVariable("tid") int tid) {
        return service.findWidgetsForTopic(tid);
    }

    @PutMapping("/api/widgets/{wid}")
    public int updateWidget(@PathVariable("wid") int wid, @RequestBody Widget updatedWidget) {
        return service.updateWidget(wid, updatedWidget);
    }

    @DeleteMapping("/api/widgets/{wid}")
    public int deleteWidget(@PathVariable("wid") Integer wid) {
        return service.deleteWidget(wid);
    }

    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return service.findAllWidgets();
    }

    @GetMapping("/api/widgets/{wid}")
    public Widget findWidgetById(@PathVariable("wid") Integer wid) {
        return service.findWidgetById(wid);
    }

}
*/