package com.example.leaflog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@ConfigurationPropertiesScan
public class LeaflogApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeaflogApplication.class, args);
    }

}
