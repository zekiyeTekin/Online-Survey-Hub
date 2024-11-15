package com.zekiyetekin.surveyhub.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String content;
    private boolean isRequired;

    private String type;

    @JoinColumn(name = "survey_id", nullable = false)
    @ManyToOne
    private Survey survey;
}
