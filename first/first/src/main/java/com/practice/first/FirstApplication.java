package com.practice.first;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableScheduling
public class FirstApplication {
    public static void main(String[] args) {
      SpringApplication.run(FirstApplication.class, args);
    }
    
}




