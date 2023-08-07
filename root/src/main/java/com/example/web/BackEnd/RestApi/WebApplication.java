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

//TODO Cross-Site Scripting (XSS) Prevention: Escaping and encoding user-generated content before displaying it on web pages can prevent XSS attacks. Utilize content security policies (CSP) to control which resources can be loaded. 


