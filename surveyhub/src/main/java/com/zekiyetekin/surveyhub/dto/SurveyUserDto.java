package com.zekiyetekin.surveyhub.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyUserDto {

    @JsonIgnore
    private Integer id;

    private UserDto user;

    private SurveyDto survey;

    private LocalDateTime participationDate;
}
