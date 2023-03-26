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
//    @SuppressWarnings("unchecked")
    public Map<ThreatLevel, Set<String>> checkThreats(String tag) {
        List<String> tags = Arrays.stream(tag.split("#")).toList();
        var multimap = blackList.getThreats().stream()
                .filter(blackListItem -> !Collections.disjoint(blackListItem.getTags(), tags)) //If need common words -  listA.retainAll(listB);
                .collect(Multimaps.toMultimap(BlackListItem::getLevel, BlackListItem::getName, HashMultimap::create));
        //return result = (Map<ThreatLevel, Set<String>>) (Map<?, ?>)multimap.asMap();
        return Multimaps.asMap(multimap);
    }
}
