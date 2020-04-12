package com.example.springbootserver.repositories;


import com.example.springbootserver.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository
        extends CrudRepository<User, Integer> {
    @Query("SELECT user FROM User user WHERE user.name=:name AND user.password=:password")
    public User findUserByCredentials(@Param("name")String name, @Param("password") String password);
}
