package com.example.adorsys.kafka.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@ConfigurationProperties(prefix = "twitter-clone.kafka.producer")
public record ProducerKafkaProperties(
        @NotBlank String topic,
        @NotBlank String createdEventType,
        @NotBlank String source
) {
}
