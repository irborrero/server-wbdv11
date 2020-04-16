package com.example.springbootserver.services;

import com.example.springbootserver.models.Course;
import com.example.springbootserver.models.Event;
import com.example.springbootserver.models.User;
import com.example.springbootserver.repositories.CourseRepository;
import com.example.springbootserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> findUsersToValidate() {
        return repository.findUsersToValidate();
    }

    public User save(User user){
        return repository.save(user);
    }

    public User findUserByCredentials(String name, String password){
        return repository.findUserByCredentials(name, password);
    }

    public User updateCourse(User updatedUser) {

        User newUser = repository.save(updatedUser);
        return newUser;
    }

    public void updateValidateFaculty(int userId){
        repository.updateValidateFaculty(userId);
    }

    public void updateUnvalidateFaculty(int userId){
        repository.updateUnvalidateFaculty(userId);
    }

    public List<Event> findEventsForUser(int userId){
        List<Object> list = repository.findEventsForUser(userId);
        List<Event> events = new ArrayList<>();
        for(Object event: list){
            events.add((Event) event);
        }
        return  events;
    };
}
