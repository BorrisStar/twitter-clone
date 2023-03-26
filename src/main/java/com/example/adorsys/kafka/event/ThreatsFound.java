package com.example.adorsys.kafka.event;

import com.example.adorsys.property.ThreatLevel;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@JsonTypeInfo(
        use = Id.CLASS,
        include = As.PROPERTY,
        property = "@class"
)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"threats", "tag"})
public class ThreatsFound {
    @JsonProperty("threats")
    private @Valid @NotNull Map<ThreatLevel, Set<String>> threats = new HashMap<>();
    @JsonProperty("tag")
    private @Valid @NotNull @NotBlank String tag = "";

    @JsonIgnore
    private @Valid Map<String, Object> additionalProperties = new LinkedHashMap();

    public ThreatsFound() {
    }

    public ThreatsFound(Map<ThreatLevel, Set<String>> threats, String tag) {
        this.threats = threats;
        this.tag = tag;
    }

    @JsonProperty("threats")
    public Map<ThreatLevel, Set<String>> getThreats() {
        return this.threats;
    }

    @JsonProperty("threats")
    public void setThreats(Map<ThreatLevel, Set<String>> threats) {
        this.threats = threats;
    }

    @JsonProperty("tag")
    public String getTag() {
        return this.tag;
    }

    @JsonProperty("tag")
    public void setTag(String tag) {
        this.tag = tag;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ThreatsFound that)) return false;

        if (!Objects.equals(threats, that.threats)) return false;
        return Objects.equals(tag, that.tag);
    }

    @Override
    public int hashCode() {
        int result = threats != null ? threats.hashCode() : 0;
        result = 31 * result + (tag != null ? tag.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MessageCheckedInfo{" +
                "threats=" + threats +
                ", tag='" + tag + '\'' +
                '}';
    }
}
