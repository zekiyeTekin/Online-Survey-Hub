package com.zekiyetekin.surveyhub.dto;


import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {

    private Integer id;

    private String content;
    private boolean isRequired;
    private String type;
    private List<OptionDto> options;
}
