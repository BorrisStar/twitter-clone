package com.example.adorsys.kafka;

import com.example.adorsys.kafka.event.ThreatsExistEvent;
import com.example.adorsys.utils.MeterConstants;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;

@Slf4j
@RequiredArgsConstructor
public class TagsContentCheckingProducer implements KafkaProducer<ThreatsExistEvent>{

    private final String topic;
    private final KafkaTemplate<String, ThreatsExistEvent> kafkaTemplate;
    private final MeterRegistry meterRegistry;

    @Override
    public void send(ThreatsExistEvent event) {
        try {
            kafkaTemplate.send(topic, event);
            meterRegistry.counter(MeterConstants.PROMETHEUS_THREATS_EVENT_COUNT).increment(); //com.example.adorsys.threats.event.count{} throughput=0.016667/s
        } catch (KafkaException ex) {
            log.error("Sending ThreatsExistEvent failed.", ex);
        }
    }
}
