package com.spring.springbootmicroservicesquizservice.model;

import lombok.Data;

@Data
public class QuizDto {
    private String category;
    private Integer noOfQuestions;
    private String title;
}
