package com.example.adorsys.service;

import com.example.adorsys.domain.Message;
import com.example.adorsys.domain.User;
import com.example.adorsys.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Service
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public String mainScreen(Map<String, Object> model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
        return "main";
    }

    private void findAllMessages(Map<String, Object> model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
    }

    public String add(String text, String tag, User user, Map<String, Object> model) {
        Message message = new Message(text, tag, user);
        Message messageSaved = messageRepository.save(message);
        System.out.println(messageSaved);
        findAllMessages(model);
        return "main";
    }

    public String filter(String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepository.findByTag(filter);
        } else {
            messages = messageRepository.findAll();
        }

        model.put("messages", messages);

        return "main";
    }
}