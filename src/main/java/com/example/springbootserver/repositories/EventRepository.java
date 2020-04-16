package com.example.springbootserver.repositories;


import com.example.springbootserver.models.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

public interface EventRepository
        extends CrudRepository<Event, Integer> {

//    @PersistenceContext
//    EntityManager entityManager;
    
    @Query("SELECT event FROM Event event")
    public List<Event> findAllEvents();

    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO events VALUES (:eventId, :eventTitle) ON DUPLICATE KEY UPDATE title =:eventTitle ")
    public void insertEvent(@Param("eventId") String eventId, @Param("eventTitle") String eventTitle);
}
