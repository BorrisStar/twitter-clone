package com.example.adorsys.property;

import lombok.Data;

import java.util.List;

@Data
public class BlackListItem {
    private String name;
    private ThreatLevel level;
    private List<String> tags;
}
