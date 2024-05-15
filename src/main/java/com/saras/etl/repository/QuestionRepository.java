package com.saras.etl.repository;

import com.saras.etl.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findByLabel(String label);
    List<Question> findByLanguage(String language);
}
