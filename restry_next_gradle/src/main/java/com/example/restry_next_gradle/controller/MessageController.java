package com.example.restry_next_gradle.controller;

import com.example.restry_next_gradle.domain.Message;
import com.example.restry_next_gradle.domain.User;
import com.example.restry_next_gradle.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {
    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    //read all messages
    @GetMapping
    public List<Message> list(){
        return messageRepo.findAll();
    }

    //read one message by id
    @GetMapping("{id}")
    public Message getOne(@PathVariable("id") Message message){
        return message;
    }

    //create message
    @PostMapping
    public Message create(@AuthenticationPrincipal User user, @RequestBody Message message){
        message.setAuthor(user);
        message.setCreationDate(LocalDateTime.now());
        return messageRepo.save(message);
    }

    //update message
    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ){
        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepo.save(messageFromDb);
    }

    //delete
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){
        messageRepo.delete(message);
    }
}
