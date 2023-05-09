package com.example.adorsys.kafka.event;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@JsonTypeInfo(
        use = Id.CLASS,
        include = As.PROPERTY,
        property = "@class"
)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"base", "data"})
public class ThreatsExistEvent implements CommonKafkaEvent {
    @JsonProperty("base")
    @JsonPropertyDescription("The Basic Event Structure for Kafka events. It contains the necessary metadata")
    private @Valid @NotNull KafkaEventBase base;
    @JsonProperty("data")
    private @Size(min = 1) @Valid @NotNull ThreatsFound data = new ThreatsFound();

    @JsonIgnore
    private @Valid Map<String, Object> additionalProperties = new LinkedHashMap();

    public ThreatsExistEvent() {
    }

    @JsonProperty("base")
    public KafkaEventBase getBase() {
        return this.base;
    }

    @JsonProperty("base")
    public void setBase(KafkaEventBase base) {
        this.base = base;
    }

    @JsonProperty("data")
    public ThreatsFound getData() {
        return this.data;
    }

    @JsonProperty("data")
    public void setData(ThreatsFound data) {
        this.data = data;
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
        if (!(o instanceof ThreatsExistEvent that)) return false;

        if (!Objects.equals(base, that.base)) return false;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = base != null ? base.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ThreatsExistsEvent{" +
                "base=" + base +
                ", data=" + data +
                '}';
    }
}
