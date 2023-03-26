package com.example.adorsys.kafka.event;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@JsonTypeInfo(
        use = Id.CLASS,
        include = As.PROPERTY,
        property = "@class"
)
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"topic", "source", "eventType", "eventTime", "version", "correlationId"})
public class KafkaEventBase {
    @JsonProperty("topic")
    @JsonPropertyDescription("The kafka topic")
    private @NotNull String topic;
    @JsonProperty("source")
    @JsonPropertyDescription("The service sending the event")
    private @NotNull String source;
    @JsonProperty("eventType")
    @JsonPropertyDescription("Type of the event in {BoundedContext}.{Subdomain}")
    private @Pattern(
            regexp = "^[a-zA-Z]+\\.[a-zA-Z]+"
    ) @NotNull String eventType;
    @JsonProperty("eventTime")
    @JsonPropertyDescription("The time of the event in german time")
    private @NotNull LocalDateTime eventTime;
    @JsonProperty("version")
    @JsonPropertyDescription("Version of the payload only in full versions")
    private @NotNull String version;
    @JsonProperty("correlationId")
    @JsonPropertyDescription("The correlation id to be passed through for tracing requests")
    private @NotNull UUID correlationId;
    @JsonIgnore
    private @Valid Map<String, Object> additionalProperties = new LinkedHashMap();

    public KafkaEventBase() {
    }

    @JsonProperty("topic")
    public String getTopic() {
        return this.topic;
    }

    @JsonProperty("topic")
    public void setTopic(String topic) {
        this.topic = topic;
    }

    @JsonProperty("source")
    public String getSource() {
        return this.source;
    }

    @JsonProperty("source")
    public void setSource(String source) {
        this.source = source;
    }

    @JsonProperty("eventType")
    public String getEventType() {
        return this.eventType;
    }

    @JsonProperty("eventType")
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @JsonProperty("eventTime")
    public LocalDateTime getEventTime() {
        return this.eventTime;
    }

    @JsonProperty("eventTime")
    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    @JsonProperty("version")
    public String getVersion() {
        return this.version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("correlationId")
    public UUID getCorrelationId() {
        return this.correlationId;
    }

    @JsonProperty("correlationId")
    public void setCorrelationId(UUID correlationId) {
        this.correlationId = correlationId;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(KafkaEventBase.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("topic");
        sb.append('=');
        sb.append(this.topic == null ? "<null>" : this.topic);
        sb.append(',');
        sb.append("source");
        sb.append('=');
        sb.append(this.source == null ? "<null>" : this.source);
        sb.append(',');
        sb.append("eventType");
        sb.append('=');
        sb.append(this.eventType == null ? "<null>" : this.eventType);
        sb.append(',');
        sb.append("eventTime");
        sb.append('=');
        sb.append(this.eventTime == null ? "<null>" : this.eventTime);
        sb.append(',');
        sb.append("version");
        sb.append('=');
        sb.append(this.version == null ? "<null>" : this.version);
        sb.append(',');
        sb.append("correlationId");
        sb.append('=');
        sb.append(this.correlationId == null ? "<null>" : this.correlationId);
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(this.additionalProperties == null ? "<null>" : this.additionalProperties);
        sb.append(',');
        if (sb.charAt(sb.length() - 1) == ',') {
            sb.setCharAt(sb.length() - 1, ']');
        } else {
            sb.append(']');
        }

        return sb.toString();
    }

    public int hashCode() {
        int result = 1;
        result = result * 31 + (this.eventTime == null ? 0 : this.eventTime.hashCode());
        result = result * 31 + (this.topic == null ? 0 : this.topic.hashCode());
        result = result * 31 + (this.correlationId == null ? 0 : this.correlationId.hashCode());
        result = result * 31 + (this.source == null ? 0 : this.source.hashCode());
        result = result * 31 + (this.eventType == null ? 0 : this.eventType.hashCode());
        result = result * 31 + (this.additionalProperties == null ? 0 : this.additionalProperties.hashCode());
        result = result * 31 + (this.version == null ? 0 : this.version.hashCode());
        return result;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof KafkaEventBase)) {
            return false;
        } else {
            KafkaEventBase rhs = (KafkaEventBase) other;
            return Objects.equals(this.eventTime, rhs.eventTime) && Objects.equals(this.topic, rhs.topic) && Objects.equals(this.correlationId, rhs.correlationId) && Objects.equals(this.source, rhs.source) && Objects.equals(this.eventType, rhs.eventType) && Objects.equals(this.additionalProperties, rhs.additionalProperties) && Objects.equals(this.version, rhs.version);
        }
    }

    public abstract static class KafkaEventBaseBuilderBase<T extends KafkaEventBase> {
        protected T instance;

        public KafkaEventBaseBuilderBase() {
            if (this.getClass().equals(KafkaEventBaseBuilder.class)) {
                this.instance = (T) new KafkaEventBase();
            }

        }

        public T build() {
            T result = this.instance;
            this.instance = null;
            return result;
        }

        public KafkaEventBaseBuilderBase withTopic(String topic) {
            this.instance.setTopic(topic);
            return this;
        }

        public KafkaEventBaseBuilderBase withSource(String source) {
            this.instance.setSource(source);
            return this;
        }

        public KafkaEventBaseBuilderBase withEventType(String eventType) {
            this.instance.setEventType(eventType);
            return this;
        }

        public KafkaEventBaseBuilderBase withEventTime(LocalDateTime eventTime) {
            this.instance.setEventTime(eventTime);
            return this;
        }

        public KafkaEventBaseBuilderBase withVersion(String version) {
            this.instance.setVersion(version);
            return this;
        }

        public KafkaEventBaseBuilderBase withCorrelationId(UUID correlationId) {
            this.instance.setCorrelationId(correlationId);
            return this;
        }

        public KafkaEventBaseBuilderBase withAdditionalProperty(String name, Object value) {
            this.instance.getAdditionalProperties().put(name, value);
            return this;
        }
    }

    public static class KafkaEventBaseBuilder extends KafkaEventBaseBuilderBase<KafkaEventBase> {
        public KafkaEventBaseBuilder() {
        }
    }
}

