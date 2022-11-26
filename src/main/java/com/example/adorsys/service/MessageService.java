package com.example.adorsys.service;

import com.example.adorsys.domain.Message;
import com.example.adorsys.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

    public String add(String text, String tag,Map<String, Object> model) {
        Message message = new Message(text, tag);
        Message messageSaved = messageRepository.save(message);
        Iterable<Message> messages = messageRepository.findAll();
        model.put("messages", messages);
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
