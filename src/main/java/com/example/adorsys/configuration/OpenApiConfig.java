package com.example.adorsys.configuration;

import com.example.adorsys.ApiClient;
import com.example.adorsys.api.DefaultApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OpenApiConfig {
    @Bean
    public DefaultApi defaultApi() {
        return new DefaultApi(apiClient());
    }

    @Bean
    public ApiClient apiClient() {
        return new ApiClient();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
