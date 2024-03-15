package com.spring.springbootmicroservicesquizservice.service;

import com.spring.springbootmicroservicesquizservice.dao.QuizDao;
import com.spring.springbootmicroservicesquizservice.feign.QuizInterface;
import com.spring.springbootmicroservicesquizservice.model.*;
import org.bouncycastle.util.Integers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, String title, int numberOfQuestions) {
        try
        {

            List<Integer> questionsIds = quizInterface.generateQuestions(category, numberOfQuestions).getBody();


            Quiz quiz = new Quiz();
            quiz.setQuestionIds(questionsIds);
            quiz.setTitle(title);

            quizDao.save(quiz);
            return new ResponseEntity<>("Quiz created successfully", org.springframework.http.HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Failed to create quiz", org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(int id) {
        try
        {
            Quiz quiz = quizDao.findById(id).get();
            List<Integer> questionIds = quiz.getQuestionIds();
            List<QuestionWrapper> questionsForUser = quizInterface.getQuestionsFromIds(questionIds).getBody();

            return new ResponseEntity<>(questionsForUser, org.springframework.http.HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Integer> submitQuiz(int id, List<QuizAnswer> quizAnswers) {
        try
        {
            int score = quizInterface.getScore(quizAnswers).getBody();
            return new ResponseEntity<>(score, org.springframework.http.HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
