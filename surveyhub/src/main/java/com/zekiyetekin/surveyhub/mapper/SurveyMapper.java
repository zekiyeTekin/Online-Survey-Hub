package com.zekiyetekin.surveyhub.mapper;

import com.zekiyetekin.surveyhub.dto.SurveyDto;
import com.zekiyetekin.surveyhub.entity.Survey;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyMapper {

    private final QuestionMapper questionMapper;
    public SurveyMapper(QuestionMapper questionMapper){
        this.questionMapper = questionMapper;
    }

    public SurveyDto toDto(Survey survey){
        return SurveyDto.builder()
                .id(survey.getId())
                .name(survey.getName())
                .description(survey.getDescription())
                .category(survey.getCategory())
                .questionCount(survey.getQuestionCount())
                .isVisibility(survey.isVisibility())
                .createdAt(survey.getCreatedAt())
                .deadlineDate(survey.getDeadlineDate())
                .questions(questionMapper.convertList(survey.getQuestions()))
                .build();
    }

    public List<SurveyDto> convertList(List<Survey> surveyList){
        List<SurveyDto> surveyDtoList = new ArrayList<>();
        for (Survey survey : surveyList){
            surveyDtoList.add(toDto(survey));
        }
        return surveyDtoList;
    }
}
