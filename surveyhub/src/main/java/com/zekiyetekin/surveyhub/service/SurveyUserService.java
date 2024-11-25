package com.zekiyetekin.surveyhub.service;

import com.zekiyetekin.surveyhub.dto.SurveyDto;
import com.zekiyetekin.surveyhub.dto.SurveyUserDto;
import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.Survey;
import com.zekiyetekin.surveyhub.entity.User;

import java.util.List;

public interface SurveyUserService {

    ResponseModel<List<SurveyUserDto>> getUsersBySurvey(Survey survey);
    ResponseModel<List<SurveyUserDto>> getParticipatedSurveys(User user);
    ResponseModel<SurveyUserDto> participateSurvey(Integer surveyId, Integer userId);
    ResponseModel<List<SurveyDto>> getNotParticipatedSurveys(User user);

}
