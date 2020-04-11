package com.example.springbootserver.repositories;


import com.example.springbootserver.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository
        extends CrudRepository<User, Integer> {
}
