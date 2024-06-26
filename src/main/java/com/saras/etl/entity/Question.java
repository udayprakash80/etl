package com.saras.etl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String label;
    @OneToMany(mappedBy = "question",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;
    private String type;
    private String level;
    private String language;
    private boolean active;
    private Long sequence;
    @Transient
    private String status;
    @Transient
    private boolean visited;


    public void setAnswers(List<Answer> answers){
        this.answers = answers;
        for(Answer answer: answers){
            answer.setQuestion(this);
        }
    }

}
