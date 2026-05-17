package com.example.cmstestingproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CmsTestingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsTestingProjectApplication.class, args);
        System.out.println("Testing Application Started");
    }

}
