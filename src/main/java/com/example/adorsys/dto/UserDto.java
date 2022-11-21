package com.example.adorsys.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull
    @NotBlank
    private final String username;
    @NotNull
    @NotBlank
    private final String password;

    @NotNull
    @NotBlank
    @Email
    private String email;

    private String activationCode;
}
