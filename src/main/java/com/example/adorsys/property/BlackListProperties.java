package com.example.adorsys.property;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "blacklist")
public class BlackListProperties {
    private List<BlackListItem> threats;
}
