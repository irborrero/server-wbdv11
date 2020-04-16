package com.example.springbootserver.controllers;

import com.example.springbootserver.models.Course;
import com.example.springbootserver.models.Event;
import com.example.springbootserver.models.User;
import com.example.springbootserver.repositories.UserRepository;
import com.example.springbootserver.services.CourseService;
import com.example.springbootserver.services.EventService;
import com.example.springbootserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {
    @Autowired
    UserService service;

    @Autowired
    EventService eventService;

    @PostMapping("/register")
    public User register(
            HttpSession session,
            @RequestBody User user) {
        user.setValidated(Boolean.FALSE);
        User newUser = service.save(user);
        newUser.setPassword("***");
        session.setAttribute("profile", newUser);
        return newUser;
    }
    @PostMapping("/profile")
    public User profile(HttpSession session) {
        User profile = (User)session.getAttribute("profile");
        profile.setPassword("***");
        return profile;
    }
    @PostMapping("/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }
    @PostMapping("/login")
    public User login(
            HttpSession session,
            @RequestBody User user) {

        try {
            User currentUser = service.findUserByCredentials(user.getName(), user.getPassword());
            //currentUser.setPassword("***");
            session.setAttribute("profile", currentUser);
            return currentUser ;
        } catch (java.lang.NullPointerException e){
            return user;
        }
    }
    @GetMapping("/users/faculty/validate")
    public List<User> findUserToValidate() {
        return service.findUsersToValidate();
    }
    @PutMapping("/users/faculty/validate/{userId}")
    public void updateValidateFaculty(@PathVariable("userId") int userId) {
        service.updateValidateFaculty(userId);
    }
    @PutMapping("/users/faculty/unvalidate/{userId}")
    public void updateUnvalidateFaculty(@PathVariable("userId") int userId) {
        service.updateUnvalidateFaculty(userId);
    }


    @PostMapping("/users/events/")
    public void saveEvent(HttpSession session, @RequestBody Event newEvent) {
        User profile = (User)session.getAttribute("profile");
        eventService.insertEvent(newEvent);
        profile.getEvents().add(newEvent);
        service.save(profile);
    }

    @GetMapping("/users/events/")
    public List<Event> findEventsForUser(HttpSession session) {
        User profile = (User)session.getAttribute("profile");
        return  service.findEventsForUser(profile.getId());

    }
}
