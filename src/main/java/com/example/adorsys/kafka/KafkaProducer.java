package com.example.adorsys.kafka;


public interface KafkaProducer<E> {
    void send(E event);
}
