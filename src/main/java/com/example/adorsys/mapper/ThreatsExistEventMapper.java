package com.example.adorsys.mapper;

import com.example.adorsys.kafka.config.ProducerKafkaProperties;
import com.example.adorsys.kafka.event.KafkaEventBase;
import com.example.adorsys.kafka.event.ThreatsExistEvent;
import com.example.adorsys.kafka.event.ThreatsFound;
import com.example.adorsys.property.ThreatLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ThreatsExistEventMapper {
    private final ProducerKafkaProperties producerKafkaProperties;

    public ThreatsExistEvent createEvent(Map<ThreatLevel, Set<String>> threats, String message) {
        KafkaEventBase base = createEventBase();
        ThreatsExistEvent event = createEventPayload(threats, message);
        event.setBase(base);
        return event;
    }

    private ThreatsExistEvent createEventPayload(Map<ThreatLevel, Set<String>> threats, String message) {
        var event = new ThreatsExistEvent();
        event.setData(new ThreatsFound(threats, message));
        return event;
    }

    private KafkaEventBase createEventBase() {
        return new KafkaEventBase.KafkaEventBaseBuilder()
                .withSource(producerKafkaProperties.source())
                .withEventTime(LocalDateTime.now())
                .withEventType(producerKafkaProperties.createdEventType())
                .withTopic(producerKafkaProperties.topic())
                .withVersion("1")
                .withCorrelationId(UUID.randomUUID())
                .build();
    }
}
