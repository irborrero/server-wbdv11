package com.example.springbootserver.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "events")
public class Event {

    @Id
    private String id;

    private String title;

    @JsonIgnore
    @ManyToMany(mappedBy = "events", fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();;


    @ManyToOne
    private Course course;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<User> getUser() {
        return users;
    }

    public void setUser(Set<User> user) {
        this.users = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
