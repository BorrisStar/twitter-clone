package com.example.adorsys.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MessageDto {

    @NotNull
    @NotBlank(message = "Please fill the message")
    @Length(max = 2048, message = "Message too long (more than 2kB)")
    String text;

    @NotNull
    @NotBlank(message = "Please fill the tag")
    @Length(max = 255, message = "Tag too long (more than 255)")
    String tag;
}
