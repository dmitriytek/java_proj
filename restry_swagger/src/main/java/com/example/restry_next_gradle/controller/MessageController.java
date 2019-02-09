package com.example.restry_next_gradle.controller;

import com.example.restry_next_gradle.domain.Message;
import com.example.restry_next_gradle.repo.MessageRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/message")
@Api(description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Messages.")
public class MessageController {
    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    //read all messages
    @GetMapping
    @ApiOperation("Returns list of all Messages in the system.")
    public List<Message> list(){
        return messageRepo.findAll();
    }

    //read one message by id\
    //@ApiImplicitParam(name = "id", value = "Message ID", required = true, dataType = "long", paramType = "path")
//    @GetMapping("/show/{id}")
//    public Message getOne(@PathVariable("id") Message message){
//        return message;
//    }
    @GetMapping("{id}")
    @ApiOperation("Returns a specific message by their identifier. 404 if does not exist.")
    public Message getOne(@PathVariable("id") String message_id){
        return messageRepo.findById(Long.parseLong(message_id)).get();
    }

    //create message
    @PostMapping
    @ApiOperation("Creates a new message.")
    public Message create(@RequestBody Message message){
        message.setCreationDate(LocalDateTime.now());
        return messageRepo.save(message);
    }

    //update message
//    @PutMapping("/update/{id}")
//    public Message update(
//            @PathVariable("id") Message messageFromDb,
//            @RequestBody Message message
//    ){
//        BeanUtils.copyProperties(message, messageFromDb, "id");
//
//        return messageRepo.save(messageFromDb);
//    }
    @PutMapping("{id}")
    @ApiOperation("Updates a specific message by their identifier. 404 if does not exist.")
    public Message update(
            @PathVariable("id") String message_id,
            @RequestBody Message message
    ){
        Message messageFromDb = messageRepo.findById(Long.parseLong(message_id)).get();
        BeanUtils.copyProperties(message, messageFromDb, "id");

        return messageRepo.save(messageFromDb);
    }

    //delete
//    @DeleteMapping("/delete/{id}")
//    public void delete(@PathVariable("id") Message message){
//        messageRepo.delete(message);
//    }
    @DeleteMapping("{id}")
    @ApiOperation("Deletes a message from the system. 404 if the message's identifier is not found.")
    public void delete(@PathVariable("id") String message_id){
        messageRepo.delete(messageRepo.findById(Long.parseLong(message_id)).get());
    }
}
