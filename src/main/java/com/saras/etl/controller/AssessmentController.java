package com.saras.etl.controller;

import com.saras.etl.entity.Question;
import com.saras.etl.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AssessmentController {

    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("assessment/getQuestions")
    public @ResponseBody List<Question> getQuestions() {
        return this.questionRepository.findAll();
    }
}
