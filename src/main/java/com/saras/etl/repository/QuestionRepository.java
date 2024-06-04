package com.saras.etl.repository;

import com.saras.etl.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findByLabel(String label);
    List<Question> findByLanguage(String language);

    @Query(nativeQuery = true, value="select language from Question group by language")
    List<String> findAllLanguage();
}
