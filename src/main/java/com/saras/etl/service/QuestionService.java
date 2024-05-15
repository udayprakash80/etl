package com.saras.etl.service;

import com.saras.etl.entity.Answer;
import com.saras.etl.entity.Question;
import com.saras.etl.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;
    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id){
        return questionRepository.findById(id).orElse(null);
    }

    public List<Question> getQuestionsByLanguage(String language){
        return questionRepository.findByLanguage(language);
    }
    public Question saveQuestion(Question question){
        return questionRepository.save(question);
    }

    public List<Question> saveAllQuestions(List<Question> questions){
        return questionRepository.saveAll(questions);
    }
    public List<Question> updateAllQuestions(List<Question> questions){
        List<Question> updatedQuestions = new ArrayList<>();
        for(Question question: questions){
            Question existingQuestion = questionRepository.findByLabel(question.getLabel());
            if(existingQuestion.getId() > 0){
                question.setId(existingQuestion.getId());
            }
            updatedQuestions.add(questionRepository.save(question));
        }
        return questionRepository.saveAll(updatedQuestions);
    }

    public Question updateQuestion(Question updateQuestion){
        Optional<Question> optionalUser = questionRepository.findById(updateQuestion.getId());
        if(optionalUser.isPresent()){
            Question existingQuestion = optionalUser.get();
            existingQuestion.setLabel(updateQuestion.getLabel());
            existingQuestion.setLanguage(updateQuestion.getLanguage());
            existingQuestion.setType(updateQuestion.getType());
            existingQuestion.setLevel(updateQuestion.getLevel());

            existingQuestion.getAnswers().clear();
            for(Answer answer: updateQuestion.getAnswers()){
                answer.setQuestion(existingQuestion);
                existingQuestion.getAnswers().add(answer);
            }
            return questionRepository.save(existingQuestion);
        }
        return questionRepository.save(updateQuestion);
    }

    public void deleteQuestion(Long id){
        questionRepository.deleteById(id);
    }
}
