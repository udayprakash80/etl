package com.saras.etl.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.saras.etl.entity.Answer;
import com.saras.etl.entity.Question;
import com.saras.etl.entity.Views;
import com.saras.etl.model.AssessmentResult;
import com.saras.etl.repository.AnswerRepository;
import com.saras.etl.repository.QuestionRepository;
import com.saras.etl.utility.AssessmentMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnswerRepository answerRepository;

    public List<Question> getAllQuestion(){
        return questionRepository.findAll();
    }

    public Question getQuestionById(Long id){
        return questionRepository.findById(id).orElse(null);
    }

    public List<Question> getQuestionsByLanguage(String language) {
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
            if(existingQuestion != null && existingQuestion.getId() > 0){
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

    public AssessmentResult getResult(List<Question> questions){
        AssessmentResult assessmentResult = new AssessmentResult();
        AtomicInteger incorrect = new AtomicInteger(0);
        AtomicInteger skipped = new AtomicInteger(0);
        if(questions == null || questions.isEmpty()){
            return assessmentResult;
        }
        String language = questions.get(0).getLanguage();
        if(language != null){
            List<Question> existingQuestions = questionRepository.findByLanguage(language);
            questions.forEach( question -> {
                existingQuestions.stream().filter(eq -> eq.getId().equals(question.getId()))
                        .findFirst()
                        .ifPresent(existingQuestion -> {
                            if(question.getAnswers().size() == existingQuestion.getAnswers().size()){
                                long countFalse = question.getAnswers().stream().filter(answer -> !answer.isOption()).count();
                                question.getAnswers().forEach(answer ->
                                        existingQuestion.getAnswers().stream()
                                                .filter(existingAnswer -> existingAnswer.getId().equals(answer.getId()))
                                                .findFirst()
                                                .ifPresent(existingAnswer -> answer.setCorrectAnswer(existingAnswer.isCorrect())));
                                if(countFalse == question.getAnswers().size()){
                                    skipped.incrementAndGet();
                                    question.setStatus("Skipped");
                                } else{
                                    boolean isIncorrect = question.getAnswers().stream()
                                            .anyMatch(userAnswer -> existingQuestion.getAnswers().stream()
                                                    .anyMatch(existingAnswer -> userAnswer.getId().equals(existingAnswer.getId())
                                                            && userAnswer.isOption() != existingAnswer.isCorrect()));
                                    if(isIncorrect){
                                        incorrect.incrementAndGet();
                                        question.setStatus("Wrong");
                                    }
                                }
                            }
                        });
            });
        }
        assessmentResult.setIncorrect(incorrect.get());
        assessmentResult.setSkipped(skipped.get());
        assessmentResult.setCorrect(questions.size() - incorrect.get() - skipped.get());
        assessmentResult.setTotal(questions.size());
        assessmentResult.setPercentage(AssessmentMath.calculatePercentage(assessmentResult.getCorrect(), assessmentResult.getTotal()));
        if(assessmentResult.getPercentage() >= 90){
         assessmentResult.setFeedback("EXCELLENT");
         assessmentResult.setRemark("Excellent job! You demonstrated a strong understanding of the material. Keep up the good work.");
        } else if( assessmentResult.getPercentage() >= 80 && assessmentResult.getPercentage() < 90){
         assessmentResult.setFeedback("Good");
         assessmentResult.setRemark("Great job! You are almost there, Try again");
        } else {
         assessmentResult.setFeedback("Poor");
         assessmentResult.setRemark("Ok! You need to study more,  Try again.");
        }
        assessmentResult.setQuestions(questions);
        return assessmentResult;
    }

    public List<String> getAllLanguage(){
        return questionRepository.findAllLanguage();
    }


}
