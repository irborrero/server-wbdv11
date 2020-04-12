package com.example.springbootserver.controllers;

import com.example.springbootserver.models.User;
import com.example.springbootserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {
    @Autowired
    UserRepository repository;
    @PostMapping("/register")
    public User register(
            HttpSession session,
            @RequestBody User user) {
        User newUser = repository.save(user);
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
            User currentUser = repository.findUserByCredentials(user.getName(), user.getPassword());
            currentUser.setPassword("***");
            session.setAttribute("profile", currentUser);
            return currentUser ;
        } catch (java.lang.NullPointerException e){
            return user;
        }


    }
}
