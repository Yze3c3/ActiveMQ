package com.lsl.boot.activemq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MainAPP_Produce {

    public static void main(String[] args) {
        SpringApplication.run(MainAPP_Produce.class, args);
    }
}
