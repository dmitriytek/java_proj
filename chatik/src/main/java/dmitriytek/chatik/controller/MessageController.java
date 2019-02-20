package dmitriytek.chatik.controller;

import com.fasterxml.jackson.annotation.JsonView;
import dmitriytek.chatik.config.Views;
import dmitriytek.chatik.domain.Message;
import dmitriytek.chatik.repo.MessageRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/message")
public class MessageController {
    private final MessageRepo messageRepo;

    public MessageController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping
    @JsonView(Views.MessageList.class)
    public List<Message> getMessages(){
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    public Message getOneMessage(@PathVariable("id") Message message){
        return message;
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message){
        message.setCreationDate(LocalDateTime.now());
        return messageRepo.save(message);
    }

    @PutMapping("{id}")
    public Message updateMessage(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ){
        BeanUtils.copyProperties(message, messageFromDb, "id");
        return messageRepo.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable("id") Message message){
        messageRepo.delete(message);
    }

    //websocket
    @MessageMapping("/changeMessage")
    @SendTo("/topic/activity")
    public Message change(Message message){
        return messageRepo.save(message);
    }
}
