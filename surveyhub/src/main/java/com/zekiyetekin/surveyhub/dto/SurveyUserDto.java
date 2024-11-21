package com.zekiyetekin.surveyhub.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SurveyUserDto {

    @JsonIgnore
    private Integer id;

    private UserDto user;

    private SurveyDto survey;

    private LocalDateTime participationDate;
}
