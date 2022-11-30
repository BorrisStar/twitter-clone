package com.example.adorsys.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
@AllArgsConstructor
public class MessageService {
    public String mainScreen(String name, Model model) {
        model.addAttribute("name", name);

        return "main";
    }
}
