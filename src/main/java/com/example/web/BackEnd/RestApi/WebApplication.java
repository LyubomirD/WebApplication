package com.example.web.BackEnd.RestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication(scanBasePackages = "com.example.web.BackEnd")
@EnableWebMvc
@ControllerAdvice
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}

//TODO Secure Password Storage: Hash passwords using strong cryptographic algorithms (e.g., bcrypt) and never store plain-text passwords. 

