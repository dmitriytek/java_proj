package com.example.restry_next_gradle.repo;

import com.example.restry_next_gradle.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
