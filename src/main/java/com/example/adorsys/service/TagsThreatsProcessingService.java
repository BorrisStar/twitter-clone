package com.example.adorsys.service;

import com.example.adorsys.property.ThreatLevel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
@Slf4j
public class TagsThreatsProcessingService {

    public void treatsProcessing(Map<ThreatLevel, Set<String>> treats, String tag) {
        if (!treats.isEmpty()) {
            log.error("Treats found in tag {} ", tag);
            treats.forEach((key, value) -> log.error("{}! Prohibited content! Themes: {} ", key, value));
        }
    }
}
