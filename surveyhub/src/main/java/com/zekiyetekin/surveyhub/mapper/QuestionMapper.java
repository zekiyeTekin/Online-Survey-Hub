package com.zekiyetekin.surveyhub.mapper;

import com.zekiyetekin.surveyhub.dto.QuestionDto;
import com.zekiyetekin.surveyhub.entity.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QuestionMapper {

    private final OptionMapper optionMapper;
    public QuestionMapper(OptionMapper optionMapper){
        this.optionMapper = optionMapper;
    }

    public QuestionDto toDto(Question question){
        return QuestionDto.builder()
                .id(question.getId())
                .content(question.getContent())
                .isRequired(question.isRequired())
                .type(question.getType())
                .options(optionMapper.convertList(question.getOptions()))
                .build();
    }

    public List<QuestionDto> convertList(List<Question> questionsList){
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for(Question question : questionsList){
            questionDtoList.add(toDto(question));
        }
        return questionDtoList;
    }
}
