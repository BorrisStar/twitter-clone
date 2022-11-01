package com.example.adorsys.dto;

import lombok.Data;

@Data
public class UserDto {

    @NotNull
    @NotBlank
    private final String username;
    @NotNull
    @NotBlank
    private final String password;
}

