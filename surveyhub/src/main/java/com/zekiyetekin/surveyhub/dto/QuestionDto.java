package com.zekiyetekin.surveyhub.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuestionDto {

    @JsonIgnore
    private Integer id;

    private String content;
    private boolean isRequired;
    private String type;
    private List<OptionDto> options;
}
