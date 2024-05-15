package com.saras.etl.service;

import com.saras.etl.entity.Answer;
import com.saras.etl.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AnswerService {

    @Autowired
    AnswerRepository answerRepository;

    public List<Answer> getAnswers(){
        return answerRepository.findAll();
    }

    public Answer saveAnswer(Answer answer){
        return answerRepository.save(answer);
    }

    public List<Answer> saveAllAnswers(List<Answer> answers){
        return answerRepository.saveAll(answers);
    }
}
