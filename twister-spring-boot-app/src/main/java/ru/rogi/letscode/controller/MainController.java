package ru.rogi.letscode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.rogi.letscode.domain.Message;
import ru.rogi.letscode.domain.User;
import ru.rogi.letscode.repos.MessageRepo;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private MessageRepo repo;

    @GetMapping("/")
    public String greeting(Model model){
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
            Model model){
        Iterable<Message> messages = repo.findAll();
        if (filter != null && !filter.isEmpty()){
            messages = repo.findByTag(filter);
        } else messages = repo.findAll();
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model){
        Message message = new Message(text, tag, user);
        repo.save(message);
        Iterable<Message> messages = repo.findAll();
        model.put("messages", messages);
        return "main";
    }
}
