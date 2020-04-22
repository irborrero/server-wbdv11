package com.example.springbootserver.repositories;


import com.example.springbootserver.models.Event;
import com.example.springbootserver.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository
        extends CrudRepository<User, Integer> {

//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("eventQuery");
//    EntityManager em = emf.createEntityManager();
    
    
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


    //@Query(nativeQuery = true, value="SELECT event_id, title FROM user_events join events on user_events.event_id = events.id and user_events.user_id = :userId")
    @Query("SELECT event FROM Event event  WHERE event.id IN :eventIds")
    public List<Event> findEventsForUser(@Param("eventIds") List<String> eventIds);

    @Query(nativeQuery = true, value = "SELECT event_id FROM user_events WHERE user_id = :userId")
    public List<String> findEventsIdsForUser(@Param("userId") int userId);

    @Query("SELECT user FROM User user  WHERE user.id = :userId")
    public User findUserById(@Param("userId") int userId);

    @Query("SELECT user FROM User user  WHERE user.id IN :userIds")
    public List<User> findAllUsersForIdList(@Param("userIds") List<Integer> userIds);

    @Query("SELECT DISTINCT user FROM User user  WHERE user.email = :userEmail OR user.name = :userName")
    public User findUserByEmailAndName(@Param("userEmail") String userEmail, @Param("userName") String userName);
}
