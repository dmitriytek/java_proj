package dmitriytek.chatik.controller;

import dmitriytek.chatik.repo.MessageRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

//@RestController
//@RequestMapping("api/general")
//public class MainController {
//
//    @GetMapping("hello")
//    public String helloWorld(){
//        return "Hello World!";
//    }
//
//}

@Controller
@RequestMapping("/")
public class MainController {
    private final MessageRepo messageRepo;

    public MainController(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping
    public String main(Model model){
        HashMap<Object, Object> data = new HashMap<>();

        data.put("messages", messageRepo.findAll());

        model.addAttribute("frontendData", data);

        return "index";
    }

}