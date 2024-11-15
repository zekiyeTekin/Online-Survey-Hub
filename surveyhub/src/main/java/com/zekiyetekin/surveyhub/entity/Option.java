package com.zekiyetekin.surveyhub.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String content;

    @JoinColumn(name = "question_id", nullable = false)
    @ManyToOne
    private Question question;
}
