package com.example.springbootserver.models;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table (name = "Courses")
public class Course {

    @Id
    private String id;

    private String name;

    private String description;

    @OneToMany(mappedBy = "course")
    private List<Topic> topics;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private List<Event> events;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}

