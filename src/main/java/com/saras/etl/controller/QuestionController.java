package com.saras.etl.controller;

import com.saras.etl.entity.Question;
import com.saras.etl.model.AssessmentResult;
import com.saras.etl.security.SecurityConfig;
import com.saras.etl.service.QuestionService;
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
public class QuestionController {
    public static final Logger LOG = LoggerFactory.getLogger(QuestionController.class);
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
    public Question getQuestionById(@PathVariable Long id){
        return questionService.getQuestionById(id);
    }

    @GetMapping("/search")
    public List<Question> getAllQuestionByLanguage(@RequestParam String language){
        return questionService.getQuestionsByLanguage(language);
    }

    @PostMapping
    public Question saveQuestion(@RequestBody Question question){
        return questionService.saveQuestion(question);
    }

    @PostMapping("/saveAll")
    public List<Question> saveAllQuestions(@RequestBody List<Question> questions){
        return questionService.saveAllQuestions(questions);
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
        LOG.debug("Result submitted");
        return questionService.getResult(questions);
    }

}
