package com.example.adorsys.service;

import com.example.adorsys.dto.CaptchaResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class CaptchaService {
    public CaptchaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private final RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String secret;

    public boolean send(String captchaResponse, Model model){
        String url = String.format("https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s", secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha");
            return false;
        }
        return true;
    }
}
