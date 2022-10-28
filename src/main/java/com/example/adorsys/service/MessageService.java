package com.example.adorsys.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class MessageService {
    public String mainScreen(Map<String, Object> model) {
        model.put("message", "Hello, Adorsys!");

        return "main";
    }
}
