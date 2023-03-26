package com.example.adorsys.kafka;

import com.example.adorsys.kafka.event.ThreatsExistEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@RequiredArgsConstructor
public class TagsContentCheckingProducer implements KafkaProducer<ThreatsExistEvent>{

    private final String topic;
    private final KafkaTemplate<String, ThreatsExistEvent> kafkaTemplate;

    @Override
    public void send(ThreatsExistEvent event) {
        try {
            kafkaTemplate.send(topic, event);
            log.info("ThreatsExistEvent: {} published", event);
        } catch (KafkaException ex) {
            log.error("Sending ThreatsExistEvent failed.", ex);
        }
    }
}
