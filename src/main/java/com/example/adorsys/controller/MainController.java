package com.example.adorsys.controller;

import com.example.adorsys.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {
    private final MessageService messageService;

    public MainController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String mainScreen(Map<String, Object> model) {
        return messageService.mainScreen(model);
    }

}
