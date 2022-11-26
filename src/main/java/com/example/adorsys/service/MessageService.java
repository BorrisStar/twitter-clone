package com.example.adorsys.service;

import com.example.adorsys.domain.Message;
import com.example.adorsys.domain.User;
import com.example.adorsys.dto.MessageDto;
import com.example.adorsys.repository.MessageRepository;
import com.example.adorsys.utils.BindingResultErrorsUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class MessageService {
    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;

    @Value("${twitter-clone.upload-path}")
    private String uploadPath;

    public MessageService(MessageRepository messageRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
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

    private void findAllMessages(Model model) {
        Iterable<Message> messages = messageRepository.findAll();
        model.addAttribute("messages", messages);
    }

    public String add(MessageDto messageDto, User user, BindingResult bindingResult, Model model, MultipartFile file) throws IOException {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = BindingResultErrorsUtil.getErrors(bindingResult);

            model.addAttribute("errorMap", errorMap);
            model.addAttribute("message", messageDto);
        } else {
            Message message = modelMapper.map(messageDto, Message.class);
            modelMapper.validate();

            message.setAuthor(user);

            if (!file.isEmpty() && file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }
                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile.concat(".").concat(file.getOriginalFilename());
                file.transferTo(new File(uploadPath.concat("/").concat(resultFilename)));

                message.setFilename(resultFilename);
            }
            model.addAttribute("message", null);

            Message messageSaved = messageRepository.save(message);
            System.out.println(messageSaved);
        }

        findAllMessages(model);

        return "main";
    }
}
