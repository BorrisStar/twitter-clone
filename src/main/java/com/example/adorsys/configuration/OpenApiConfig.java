package com.example.adorsys.configuration;

import com.example.adorsys.ApiClient;
import com.example.adorsys.api.DefaultApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
