package com.example.restry_next_gradle.repo;

import com.example.restry_next_gradle.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
