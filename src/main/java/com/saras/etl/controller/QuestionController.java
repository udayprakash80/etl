package com.saras.etl.controller;

import com.saras.etl.entity.Question;
import com.saras.etl.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @GetMapping
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestion();
    }

}
