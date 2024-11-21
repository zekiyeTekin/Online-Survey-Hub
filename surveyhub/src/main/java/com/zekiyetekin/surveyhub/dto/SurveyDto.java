package com.zekiyetekin.surveyhub.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SurveyDto {

    private Integer id;
    private String name;
    private String description;
    private String category;
    private Integer questionCount;
    private boolean isVisibility;
    private LocalDate createdAt;
    private LocalDate deadlineDate;
    private List<QuestionDto> questions;
}
