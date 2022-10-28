package com.example.adorsys.service;

import com.example.adorsys.domain.Message;
import com.example.adorsys.domain.User;
import com.example.adorsys.repository.MessageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public String mainScreen(String tag, Model model) {
        Iterable<Message> messages = messageRepository.findAll();
        if (tag != null && !tag.isEmpty()) {
            messages = messageRepository.findByTag(tag);
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", tag);

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
}
