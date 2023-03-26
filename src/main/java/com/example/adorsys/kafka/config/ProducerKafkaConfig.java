package com.example.adorsys.kafka.config;

import com.example.adorsys.kafka.TagsContentCheckingProducer;
import com.example.adorsys.kafka.event.ThreatsExistEvent;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@ConfigurationPropertiesScan(basePackageClasses = ProducerKafkaConfig.class)
public class ProducerKafkaConfig {
    @Bean
    public TagsContentCheckingProducer kafkaProducer(
            KafkaTemplate<String, ThreatsExistEvent> kafkaTemplate,
            ProducerKafkaProperties consolidationKafkaProperties
    ){
        return new TagsContentCheckingProducer(consolidationKafkaProperties.topic(), kafkaTemplate);
    }

    @Bean
    public KafkaTemplate<String, ThreatsExistEvent> kafkaTemplate(
        DefaultKafkaProducerFactory<String, ThreatsExistEvent> populateKafkaProducerFactory) {
        return new KafkaTemplate<>(populateKafkaProducerFactory);
    }
}
