package com.example.adorsys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class TwitterCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterCloneApplication.class, args);
    }

}
