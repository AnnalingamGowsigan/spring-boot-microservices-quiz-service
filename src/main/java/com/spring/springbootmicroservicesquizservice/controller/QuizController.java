package com.spring.springbootmicroservicesquizservice.controller;

import com.spring.springbootmicroservicesquizservice.model.QuestionWrapper;
import com.spring.springbootmicroservicesquizservice.model.QuizAnswer;
import com.spring.springbootmicroservicesquizservice.model.QuizDto;
import com.spring.springbootmicroservicesquizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        try
        {
            return quizService.createQuiz(quizDto.getCategory(), quizDto.getTitle(), quizDto.getNoOfQuestions());
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Failed to create quiz", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        return quizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable int id, @RequestBody List<QuizAnswer> quizAnswers){
        return quizService.submitQuiz(id, quizAnswers);
    }
}
