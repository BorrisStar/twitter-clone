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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
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

            saveFile(message, file);

            model.addAttribute("message", null);

            Message messageSaved = messageRepository.save(message);
            System.out.println(messageSaved);
        }

        findAllMessages(model);

        return "main";
    }

    public String getUserMessages(User currentUser, User user, Model model, Message message) {
        Set<Message> messages = user.getMessages();

        model.addAttribute("messages", messages);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userMessages";
    }

    public String updateMessages(User currentUser, Long userId, Message message, String text, String tag, MultipartFile file) throws IOException {
        if (message.getAuthor().equals(currentUser)) {
            if (!StringUtils.hasLength(text)) {
                message.setText(text);
            }

            if (!StringUtils.hasLength(tag)) {
                message.setTag(tag);
            }

            saveFile(message, file);

            messageRepository.save(message);
        }

        return "redirect:/user-messages/" + userId;
    }

    private void saveFile(Message message, MultipartFile file) throws IOException {
        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile.concat(".").concat(file.getOriginalFilename());
            file.transferTo(new File(uploadPath.concat("/").concat(resultFilename)));

            message.setFilename(resultFilename);
        }
    }
}
