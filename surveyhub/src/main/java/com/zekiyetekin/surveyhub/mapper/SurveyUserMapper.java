package com.zekiyetekin.surveyhub.mapper;

import com.zekiyetekin.surveyhub.dto.SurveyUserDto;
import com.zekiyetekin.surveyhub.entity.SurveyUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SurveyUserMapper {

    private final SurveyMapper surveyMapper;
    private final UserMapper userMapper;


    public SurveyUserMapper(SurveyMapper surveyMapper, UserMapper userMapper){
        this.surveyMapper = surveyMapper;
        this.userMapper = userMapper;

    }

    public SurveyUserDto toDto(SurveyUser surveyUser){
        return SurveyUserDto.builder()
                .participationDate(surveyUser.getParticipationDate())
                .survey(surveyMapper.toDto(surveyUser.getSurvey()))
                .user(userMapper.toDto(surveyUser.getUser()))
                .build();
    }

    public List<SurveyUserDto> convertList(List<SurveyUser> surveyUserList){
        List<SurveyUserDto> surveyUserDtoList = new ArrayList<>();
        for(SurveyUser surveyUser : surveyUserList){
            surveyUserDtoList.add(toDto(surveyUser));
        }
        return surveyUserDtoList;
    }
}
