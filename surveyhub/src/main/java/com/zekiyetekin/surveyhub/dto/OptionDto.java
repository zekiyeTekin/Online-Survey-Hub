package com.zekiyetekin.surveyhub.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OptionDto {

    @JsonIgnore
    private Integer id;
    private String content;
}
