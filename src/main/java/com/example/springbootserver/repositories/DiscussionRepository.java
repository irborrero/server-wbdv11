package com.example.springbootserver.repositories;


import com.example.springbootserver.models.Discussion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DiscussionRepository extends CrudRepository<Discussion, Integer> {

    @Query("SELECT discussion from Discussion discussion")
    List<Discussion> findAllDiscussions();
}
