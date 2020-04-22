package com.example.springbootserver.controllers;

import com.example.springbootserver.models.Course;
import com.example.springbootserver.models.Event;
import com.example.springbootserver.models.User;
import com.example.springbootserver.repositories.EventRepository;
import com.example.springbootserver.repositories.UserRepository;
import com.example.springbootserver.services.CourseService;
import com.example.springbootserver.services.EventService;
import com.example.springbootserver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "https://project-react-client-t11.herokuapp.com", allowCredentials = "true")
public class UserController {
    @Autowired
    UserService service;

    @Autowired
    EventService eventService;

    @PostMapping("api/register")
    public User register(
            HttpSession session,
            @RequestBody User user) {

        User checkuser = service.findUserByEmailAndName(user.getEmail(), user.getName());
        if(checkuser != null){
            return null;
        } else {
            user.setValidated(Boolean.FALSE);
            User newUser = service.save(user);
            newUser.setPassword("***");
            session.setAttribute("profile", newUser);
            return newUser;
        }


    }
    @PostMapping("api/users/save")
    public User saveNewUserDetails(
            HttpSession session,
            @RequestBody User user) {
        User currentuser = service.findUserById(user.getId());
        currentuser.setLastName(user.getLastName());
        currentuser.setName(user.getName());
        currentuser.setEmail(user.getEmail());
        User newUser = service.save(currentuser);
        newUser.setPassword("***");
        session.setAttribute("profile", newUser);
        return newUser;
    }

    @PostMapping("api/profile")
    public User profile(HttpSession session) {
        try{
            User profile = (User)session.getAttribute("profile");
            profile.setPassword("***");
            return profile;
        } catch (NullPointerException e){
            return null;
        }
    }
    @PostMapping("api/logout")
    public void logout(HttpSession session) {
        session.invalidate();
    }
    @PostMapping("api/login")
    public User login(
            HttpSession session,
            @RequestBody User user) {

        try {
            User currentUser = service.findUserByCredentials(user.getName(), user.getPassword());
            currentUser.setPassword("***");
            session.setAttribute("profile", currentUser);
            return currentUser ;
        } catch (java.lang.NullPointerException e){
            return null;
        }
    }
    @GetMapping("api/users/faculty/validate")
    public List<User> findUserToValidate() {
        return service.findUsersToValidate();
    }
    @PutMapping("api/users/faculty/validate/{userId}")
    public void updateValidateFaculty(@PathVariable("userId") int userId) {
        service.updateValidateFaculty(userId);
    }
    @PutMapping("api/users/faculty/unvalidate/{userId}")
    public void updateUnvalidateFaculty(@PathVariable("userId") int userId) {
        service.updateUnvalidateFaculty(userId);
    }


    @PostMapping("api/users/events/")
    public List<String> saveEvent(HttpSession session, @RequestBody Event newEvent) {

        Event event = eventService.findEventById(newEvent.getId());
        if (event != null){
                User profile = (User)session.getAttribute("profile");
                User user = service.findUserById(profile.getId());
                user.getEvents().add(newEvent);
                service.save(user);
                return service.findEventIdsForUser(profile.getId());

        } else {
            User profile = (User)session.getAttribute("profile");
            eventService.insertEvent(newEvent);
            User user = service.findUserById(profile.getId());
            user.getEvents().add(newEvent);
            service.save(user);
            return service.findEventIdsForUser(profile.getId());
        }
    }

    @GetMapping("api/users/events/")
    public List<Event> findEventsForUser(HttpSession session) {
        User profile = (User)session.getAttribute("profile");
        return  service.findEventsForUser(profile.getId());
    }

    @GetMapping("api/users/events/ids")
    public List<String> findEventIdsForUser(HttpSession session) {
        User profile = (User)session.getAttribute("profile");
        return  service.findEventIdsForUser(profile.getId());
    }
    @DeleteMapping("api/users/events/")
    public List<String> deleteEventsForUser(HttpSession session, @RequestBody Event deleteEvent) {
        User profile = (User)session.getAttribute("profile");
        User user = service.findUserById(profile.getId());
        user.getEvents().removeIf(event -> event.getId().equals(deleteEvent.getId()));
        service.save(user);
        return  service.findEventIdsForUser(profile.getId());
    }

    @GetMapping("api/users/{userId}")
    public User findUserById(@PathVariable("userId") int userId){
        User user = service.findUserById(userId);
        user.setPassword("***");
        user.getEvents().clear();
        user.setLastName("***");
        user.getDiscussions().clear();
        return user;
    }
}
