package com.example.springbootserver.repositories;


import com.example.springbootserver.models.Event;
import com.example.springbootserver.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityResult;
import javax.persistence.SqlResultSetMapping;
import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository
        extends CrudRepository<User, Integer> {
    @Query("SELECT user FROM User user WHERE user.name=:name AND user.password=:password")
    public User findUserByCredentials(@Param("name")String name, @Param("password") String password);

    @Query("SELECT user FROM User user WHERE user.userType='FACULTY'")
    public List<User> findUsersToValidate();

    @Transactional
    @Modifying
    @Query("UPDATE User SET validated = TRUE WHERE id=:userId")
    public void updateValidateFaculty(@Param("userId")int userId);

    @Transactional
    @Modifying
    @Query("UPDATE User SET validated = FALSE WHERE id=:userId")
    public void updateUnvalidateFaculty(@Param("userId")int userId);


    @Query(nativeQuery = true, value="SELECT event_id, title FROM user_events join events on user_events.event_id = events.id and user_events.user_id = :userId")
    public List<Object> findEventsForUser(@Param("userId")int userId);
}
