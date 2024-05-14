package com.saras.etl.repository;

import com.saras.etl.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Answer, Long> {
}
