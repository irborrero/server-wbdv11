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

/*

package com.example.myapp.repositories;

        import com.example.myapp.models.Topic;
        import com.example.myapp.models.Widget;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.query.Param;

        import java.util.List;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {

    @Query("SELECT widget from Widget widget")
    List<Widget> findAllWidgets();

    @Query("SELECT widget FROM Widget widget WHERE widget.topic = :tid")
    List<Widget> findWidgetsForTopic(@Param("tid") Integer tid);

    @Query("SELECT widget FROM Widget widget WHERE widget.id = :wid")
    Widget findWidgetById(@Param("wid") Integer wid);

    @Query("SELECT widget FROM Widget widget WHERE widget.position =:index AND widget.topic.id=:topic")
    Widget findWidgetByOrder(@Param("index") Integer index, @Param("topic") Integer topicId);

    @Query("SELECT MAX(widget.position) FROM Widget widget WHERE widget.topic.id=:topic")
    Integer findMaxOrder(@Param("topic") Integer topicId);


}

*/
