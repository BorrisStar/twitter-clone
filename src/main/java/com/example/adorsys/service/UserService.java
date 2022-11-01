package com.example.adorsys.service;

import com.example.adorsys.domain.Role;
import com.example.adorsys.domain.User;
import com.example.adorsys.dto.UserDto;
import com.example.adorsys.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public String addUser(UserDto userDto, Map<String, Object> model) {
        Optional<User> userFromDb = userRepository.findByUsername(userDto.getUsername());
        if (userFromDb.isPresent()) {
            model.put("message", String.format("User %s exists!", userFromDb.get()));
            return "registration";
        }
        User newUser = modelMapper.map(userDto, User.class);
        modelMapper.validate();

        newUser.setActive(true);
        newUser.setRoles(Collections.singleton(Role.USER));
        userRepository.save(newUser);
        return "redirect:/login";
    }
}
