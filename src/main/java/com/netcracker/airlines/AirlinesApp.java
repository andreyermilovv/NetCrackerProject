package com.netcracker.airlines;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AirlinesApp {
    public static void main(String[] args) {
        SpringApplication.run(AirlinesApp.class, args);
    }
}
