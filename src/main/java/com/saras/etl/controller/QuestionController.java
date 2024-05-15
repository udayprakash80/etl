package com.saras.etl.controller;

import com.saras.etl.entity.Question;
import com.saras.etl.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public Question getQuestionById(@PathVariable Long id){
        return questionService.getQuestionById(id);
    }

    @PostMapping
    public Question saveQuestion(@RequestBody Question question){
        return questionService.saveQuestion(question);
    }
    @PostMapping("/list")
    public List<Question> saveQuestions(@RequestBody List<Question> questions){
        return questionService.saveQuestions(questions);
    }

    @PutMapping
    public Question updateQuestion(@RequestBody Question question){
        return questionService.updateQuestion(question);
    }

    @DeleteMapping
    public void deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
    }

}
