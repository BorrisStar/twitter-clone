package com.example.adorsys.controller;

import com.example.adorsys.domain.Message;
import com.example.adorsys.service.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/")
    public String add(
            @RequestParam String text,
            @RequestParam String tag,
            Map<String, Object> model
    ) {
        return messageService.add(text, tag, model);
    }
    @PostMapping("filter")
    public String filter(@RequestParam(required = false, defaultValue = "") String filter, Map<String, Object> model) {
        return messageService.filter(filter, model);
    }
}
