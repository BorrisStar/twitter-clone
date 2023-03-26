package com.example.adorsys.service;

import com.example.adorsys.property.BlackListItem;
import com.example.adorsys.property.BlackListProperties;
import com.example.adorsys.property.ThreatLevel;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimaps;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TagsContentCheckingService {
    private final BlackListProperties blackList;

    public Map<ThreatLevel, Set<String>> checkThreats(String tag) {
        List<String> tags = Arrays.stream(tag.split("#")).toList();
        var multimap = blackList.getThreats().stream()
                .filter(blackListItem -> !Collections.disjoint(blackListItem.getTags(), tags))
                .collect(Multimaps.toMultimap(BlackListItem::getLevel, BlackListItem::getName, HashMultimap::create));
        return Multimaps.asMap(multimap);
    }
}
