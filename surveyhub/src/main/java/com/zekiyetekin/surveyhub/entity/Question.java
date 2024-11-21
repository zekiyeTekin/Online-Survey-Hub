package com.zekiyetekin.surveyhub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String content;
    private boolean isRequired;

    private String type;

    @JsonIgnore
    @JoinColumn(name = "survey_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL)
    private Survey survey;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Option> options  = new ArrayList<>();
}
