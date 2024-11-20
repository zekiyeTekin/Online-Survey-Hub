package com.zekiyetekin.surveyhub.filter;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SurveyFilter {

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdAt;

    private String category;

    private String name;
}
