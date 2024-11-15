package com.zekiyetekin.surveyhub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String title;
    private LocalDate postedBy;
    private LocalDate createdAt;
    private String image;
    @Column(length = 5000)
    private String content;

    //maybe cannot keep this one
    private Integer participantCount;

    @JoinColumn(name = "survey_id", nullable = false)
    @ManyToOne
    private Survey survey;
}
