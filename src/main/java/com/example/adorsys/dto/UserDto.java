package com.example.adorsys.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull
    @NotBlank(message = "Username cannot be empty")
    private final String username;
    @NotNull
    @NotBlank(message = "Password cannot be empty")
    private final String password;

    @NotBlank(message = "Password confirmation cannot be empty")
    private String password2;

    @NotNull
    @Email(message = "Email is not correct")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    private String activationCode;
}
