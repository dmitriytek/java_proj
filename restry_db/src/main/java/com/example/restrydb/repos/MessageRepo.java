package com.example.restrydb.repos;

import com.example.restrydb.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);
    void deleteById(Integer id);

}
