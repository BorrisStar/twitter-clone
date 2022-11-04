package com.example.adorsys.service;

import com.example.adorsys.domain.Message;
import com.example.adorsys.domain.User;
import com.example.adorsys.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;

    @Value("${twitter-clone.upload-path}")
    private String uploadPath;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

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

    public String add(String text, String tag, User user, Map<String, Object> model, MultipartFile file) throws IOException {
        Message message = new Message(text, tag, user);
        if(!file.isEmpty() && file.getOriginalFilename()!=null && !file.getOriginalFilename().isEmpty()){
            File uploadDir = new File(uploadPath);
            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile.concat(".").concat(file.getOriginalFilename());
            file.transferTo(new File(uploadPath.concat("/").concat(resultFilename)));

            message.setFilename(resultFilename);
        }
        Message messageSaved = messageRepository.save(message);
        System.out.println(messageSaved);
        findAllMessages(model);
        return "main";
    }
}
