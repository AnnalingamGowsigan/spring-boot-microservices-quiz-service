package com.spring.springbootmicroservicesquizservice.feign;

import com.spring.springbootmicroservicesquizservice.model.QuestionWrapper;
import com.spring.springbootmicroservicesquizservice.model.QuizAnswer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "question-service")
public interface QuizInterface {
    @GetMapping("questions/generateQuestions")
    public ResponseEntity<List<Integer>> generateQuestions(@RequestParam String category, @RequestParam int numberOfQuestions);

    @PostMapping("questions/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds);

    @PostMapping("questions/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<QuizAnswer> quizAnswers);
}
