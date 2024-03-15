package com.spring.springbootmicroservicesquizservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringBootMicroservicesQuizServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMicroservicesQuizServiceApplication.class, args);
    }

}
