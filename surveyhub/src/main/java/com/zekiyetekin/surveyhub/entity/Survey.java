package com.zekiyetekin.surveyhub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "surveys")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String name;
    private String description;
    private String category;

    @Column(name = "question_count")
    private Integer questionCount;
    private boolean isVisibility;
    private LocalDate createdAt;

    @Column(name = "deadline_date")
    private LocalDate deadlineDate;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Question> questions  = new ArrayList<>();



}
