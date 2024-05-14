package com.saras.etl.service;

import com.saras.etl.entity.Question;
import com.saras.etl.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }
}
