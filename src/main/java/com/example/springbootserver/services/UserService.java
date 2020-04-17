package com.example.springbootserver.services;

import com.example.springbootserver.models.Event;
import com.example.springbootserver.models.User;
import com.example.springbootserver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    //EntityManagerFactory emf = Persistence.createEntityManagerFactory("eventQuery");
    //EntityManager em = EntityManagerFactoryUtils.getTransactionalEntityManager()

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
    public User findUserById(int userId){
        return repository.findUserById(userId);

    }

    public List<Event> findEventsForUser(int userId){
        List<String> eventIds = repository.findEventsIdsForUser(userId);
        List<Event> events = repository.findEventsForUser(eventIds);
        return events;

    };

    public List<String> findEventIdsForUser(int userId){
        return repository.findEventsIdsForUser(userId);
    };
}
