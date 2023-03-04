package com.example.adorsys.controller;

import com.example.adorsys.api.DefaultApi;
import com.example.adorsys.api.model.UserResponseDto;
import com.example.adorsys.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApiGenController extends DefaultApi {
    private final UserService userService;

    @Override
    @GetMapping("/apigen-getusers")
    public ResponseEntity<List<UserResponseDto>> getUsersWithHttpInfo(Boolean active, Integer amount) throws RestClientException {
        return userService.getUsers(active, amount);
    }
}
