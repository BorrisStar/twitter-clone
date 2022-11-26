package com.example.adorsys.service;

import com.example.adorsys.domain.Role;
import com.example.adorsys.domain.User;
import com.example.adorsys.dto.UserDto;
import com.example.adorsys.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MailSender mailSender;
    private final PasswordEncoder passwordEncoder;

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
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(newUser);

        sendMessage(newUser);
        return "redirect:/login";
    }

    private void sendMessage(User newUser) {
        if (StringUtils.hasLength(newUser.getEmail())) {
            String message = String.format(
                    "Hello, %s! \n" +
                            "Welcome to Twitter-clone. Please, visit next link: http://localhost:8080/activate/%s",
                    newUser.getUsername(),
                    newUser.getActivationCode()
            );

            mailSender.send(newUser.getEmail(), "Activation code", message);
        }
    }

    public String activateUser(String code, Model model) {
        Optional<User> user = userRepository.findByActivationCode(code);

        if (user.isEmpty()) {
            model.addAttribute("message", "Activation code is not found!");
        } else {
            User userActivated =  user.get();
            userActivated.setActivationCode(null);
            userRepository.save(userActivated);
            model.addAttribute("message", "User successfully activated");
        }

        return "login";
    }

    public void saveUser(String username, Map<String, String> form, User user) {
        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key: form.keySet()) {
            if(roles.contains(key)){
                user.getRoles().add(Role.valueOf(key));
            }
        }
        save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();

        boolean isEmailChanged = isEmailChanged(email, userEmail);

        if (isEmailChanged) {
            user.setEmail(email);

            if (StringUtils.hasLength(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }

        if (StringUtils.hasLength(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }

        userRepository.save(user);

        if (isEmailChanged) {
            sendMessage(user);
        }
    }

    private static boolean isEmailChanged(String email, String userEmail) {
        return (email != null && !email.equals(userEmail)) ||
                (userEmail != null && !userEmail.equals(email));
    }
}
