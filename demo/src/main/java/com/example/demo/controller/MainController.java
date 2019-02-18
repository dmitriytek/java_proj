package com.example.demo.controller;

import com.example.demo.domain.Message;
import com.example.demo.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/general")
public class MainController {
    public final MessageRepo messageRepo;

    public MainController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("hello")
    public String helloWorld(){
        return "Hello World!";
    }

    @GetMapping("message")
    public List<Message> getMessages(){
        return messageRepo.findAll();
    }

    @GetMapping("message/{id}")
    public Message getOneMessage(@PathVariable("id") Message message){
        return message;
    }

    @PostMapping("message")
    public Message createMessage(@RequestBody Message message){
        return messageRepo.save(message);
    }

    @PutMapping("message/{id}")
    public Message updateMessage(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ){
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("message/{id}")
    public void deleteMessage(@PathVariable("id") Message message){
        messageRepo.delete(message);
    }
}
