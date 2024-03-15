package com.spring.springbootmicroservicesquizservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizAnswer {
    private Integer id;
    private String answer;
}
