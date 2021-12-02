package com.airqualityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AirQualityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirQualityServiceApplication.class, args);
    }

}
