package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.demo") // Specify the base package for repository scanning
public class FirstProject1Application {

    public static void main(String[] args) {
        SpringApplication.run(FirstProject1Application.class, args);
    }

}
