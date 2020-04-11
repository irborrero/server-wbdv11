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
}

/*
 package com.example.myapp.services;

         import com.example.myapp.models.Topic;
         import com.example.myapp.models.Widget;
         import com.example.myapp.repositories.TopicRepository;
         import com.example.myapp.repositories.WidgetRepository;
         import org.springframework.beans.factory.annotation.Autowired;
         import org.springframework.stereotype.Service;


         import java.util.List;


@Service
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    TopicRepository topicRepository;

    public Widget createWidget(int tid, Widget newWidget) {
        Topic topic = topicRepository.findTopicById(tid);
        newWidget.setTopic(topic);
        newWidget.setPosition(findWidgetsForTopic(tid).size());
        return widgetRepository.save(newWidget);
    }

    public List<Widget> findWidgetsForTopic(int tid) {
        Topic topic = topicRepository.findTopicById(tid);
        return topic.getWidgets();
    }

    private Widget setOrder(Widget oldWidget, Widget newWidget){
        int oldOrder = oldWidget.getPosition();
        int newOrder = newWidget.getPosition();
        Widget tempWidget = null;
        Topic topic = oldWidget.getTopic();
        int maxOrder  = widgetRepository.findMaxOrder(topic.getId());


        if(oldOrder > newOrder && oldOrder > 0) {
            tempWidget = widgetRepository.findWidgetByOrder(oldOrder-1, topic.getId());
            tempWidget.setPosition(oldOrder);
        } else if (oldOrder < newOrder && oldOrder < maxOrder){
            tempWidget = widgetRepository.findWidgetByOrder(oldOrder+1, topic.getId());
            tempWidget.setPosition(oldOrder);
        }

        return tempWidget;
    }

    public int updateWidget(Integer wid, Widget updatedWidget) {
        if(updatedWidget == null)
            return 0;
        Widget widget = widgetRepository.findWidgetById(wid);
        updatedWidget.setTopic(widget.getTopic());
        if(widget.getPosition() != updatedWidget.getPosition()) {
            Widget tempWidget = setOrder(widget, updatedWidget);
            widgetRepository.save(tempWidget);
        }
        widgetRepository.save(updatedWidget);


        return 1;
    }

    public int deleteWidget(int wid){
        List<Widget> widgets = widgetRepository.findAllWidgets();
        widgetRepository.deleteById(wid);
        if(widgets.size() > widgetRepository.findAllWidgets().size())
            return 1;
        else return 0;
    }


    public List<Widget> findAllWidgets() {
        return widgetRepository.findAllWidgets();
    }


    public Widget findWidgetById(Integer wid) {
        return widgetRepository.findWidgetById(wid);
    }

}
*/