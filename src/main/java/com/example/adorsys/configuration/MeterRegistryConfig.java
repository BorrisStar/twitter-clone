package com.example.adorsys.configuration;

import io.micrometer.core.instrument.logging.LoggingMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeterRegistryConfig {

    @Bean
    public LoggingMeterRegistry loggingMeterRegistry() {
        return new LoggingMeterRegistry();
    }
}
