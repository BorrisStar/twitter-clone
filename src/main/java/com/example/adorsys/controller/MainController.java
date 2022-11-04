package com.example.adorsys.controller;

import com.example.adorsys.domain.User;
import com.example.adorsys.service.MessageService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {
    private final MessageService messageService;

    public MainController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String mainScreen(@RequestParam (required = false, defaultValue = "") String filter, Model model) {
        return messageService.mainScreen(filter, model);
    }

    @PostMapping("/")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam @NotNull String text,
            @RequestParam @NotNull String tag, Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        return messageService.add(text, tag, user, model, file);
    }
}
