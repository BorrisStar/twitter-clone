package com.example.adorsys.kafka;

import com.example.adorsys.kafka.event.ThreatsExistEvent;
import com.example.adorsys.property.ThreatLevel;
import com.example.adorsys.service.TagsThreatsProcessingService;
import com.google.common.collect.Multimap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class TagsContentCheckingConsumer {
    private final TagsThreatsProcessingService tagsThreatsProcessingService;

    @KafkaListener(groupId = "${twitter-clone.kafka.consumer.groupId}", topics = "${twitter-clone.kafka.consumer.topic}")
    public void processMerchantCreateEvent(ThreatsExistEvent event) {
        log.info("Threat processing started event {}", event);

        Map<ThreatLevel, Set<String>> treats = event.getData().getThreats();
        String tag = event.getData().getTag();

        try {
            tagsThreatsProcessingService.treatsProcessing(treats, tag);
        } catch (Exception ex) {
            log.error("Threat processing finished with exception", ex);
        }
        log.info("Threat processing event {} finished successfully", event);
    }
}
