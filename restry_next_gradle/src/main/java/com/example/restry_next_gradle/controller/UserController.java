package com.example.restry_next_gradle.controller;

import com.example.restry_next_gradle.domain.User;
import com.example.restry_next_gradle.repo.UserDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {
    private final UserDetailsRepo userDetailsRepo;

    @Autowired
    public UserController(UserDetailsRepo userDetailsRepo) {
        this.userDetailsRepo = userDetailsRepo;
    }

    @GetMapping
    public User read(@AuthenticationPrincipal User user){
        return user;
    }

}
