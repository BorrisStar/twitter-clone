package com.example.adorsys.controller;

import com.example.adorsys.domain.User;
import com.example.adorsys.service.MessageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.Map;

@Controller
public class MainController {
    private final MessageService messageService;

    public MainController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String mainScreen(@RequestParam (required = false, defaultValue = "") String tag, Model model) {
        return messageService.mainScreen(tag, model);
    }

    @PostMapping("/")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam @javax.validation.constraints.NotNull String text,
            @RequestParam @NotNull String tag, Map<String, Object> model
    ) {
        return messageService.add(text, tag, user, model);
    }
}
