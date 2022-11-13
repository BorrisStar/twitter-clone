package com.example.adorsys.service;

import com.example.adorsys.domain.Role;
import com.example.adorsys.domain.User;
import com.example.adorsys.dto.UserDto;
import com.example.adorsys.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MailSender mailSender;

    public List<User> findAll (){
        return userRepository.findAll() ;
    }

    public void save (User user){
        userRepository.save(user) ;
    }

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
        newUser.setActivationCode(UUID.randomUUID().toString());
        userRepository.save(newUser);

        if (!StringUtils.hasLength(userDto.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Twitter-clone. Please, visit next link: http://localhost:8080/activate/%s",
                    userDto.getUsername(),
                    userDto.getActivationCode()
            );

            mailSender.send(userDto.getEmail(), "Activation code", message);
        }
        return "redirect:/login";
    }

    public void activateUser(String code, Model model) {
        Optional<User> user = userRepository.findByActivationCode(code);

        if (user.isEmpty()) {
            model.addAttribute("message", "Activation code is not found!");
        } else {
            User userActivated =  user.get();
            userActivated.setActivationCode(null);
            userRepository.save(userActivated);
            model.addAttribute("message", "User successfully activated");
        }
    }
}
