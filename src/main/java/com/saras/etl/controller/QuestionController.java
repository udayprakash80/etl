package com.saras.etl.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.saras.etl.entity.Question;
import com.saras.etl.entity.Views;
import com.saras.etl.model.AssessmentResult;
import com.saras.etl.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestController
@RequestMapping("/questions")
@Tag(name="Question Controller", description = "API for Question")
public class QuestionController {
    public static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
    @Autowired
    private QuestionService questionService;

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String error = "The IDENTIFIER you entered is invalid, as it should contain only numbers.";
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public List<Question> getAllQuestions(){
        return questionService.getAllQuestion();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get question by ID")
    public Question getQuestionById(@PathVariable Long id){
        return questionService.getQuestionById(id);
    }

    @JsonView(Views.Public.class)
    @GetMapping("/search")
    public List<Question> getAllQuestionByLanguage(@RequestParam String language, @RequestParam int limit) {
        logger.info("Calling search api by language");
        return questionService.getQuestionsByLanguage(language, limit);
    }

    @JsonView(Views.Admin.class)
    @GetMapping("/adminSearch")
    public List<Question> getAdminAllQuestionByLanguage(@RequestParam String language) {
        return questionService.getAllQuestionsByLanguage(language);
    }

    @PostMapping("/save")
    public List<Question> saveQuestion(@RequestBody Question question){
        return questionService.saveQuestion(question);
    }

    @PostMapping("/saveAll")
    public List<Question> saveAllQuestions(@RequestBody List<Question> questions){
        return questionService.saveAllQuestions(questions);
    }

    @GetMapping("/languageList")
    public List<String> getAllLanguage(){
        return questionService.getAllLanguage();
    }

    @PostMapping("/updateAll")
    public List<Question> updateAllQuestions(@RequestBody List<Question> questions){
        return questionService.updateAllQuestions(questions);
    }

    @PutMapping
    public Question updateQuestion(@RequestBody Question question){
        return questionService.updateQuestion(question);
    }

    @DeleteMapping
    public void deleteQuestion(@PathVariable Long id){
        questionService.deleteQuestion(id);
    }

    @PostMapping("/result")
    public AssessmentResult getResult(@RequestBody List<Question> questions){
        logger.debug("Result submitted");
        return questionService.getResult(questions);
    }

}
