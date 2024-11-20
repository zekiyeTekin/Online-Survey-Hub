package com.zekiyetekin.surveyhub.service;

import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.Survey;
import com.zekiyetekin.surveyhub.entity.SurveyUser;
import com.zekiyetekin.surveyhub.entity.User;

import java.util.List;

public interface SurveyUserService {

    ResponseModel<List<SurveyUser>> getUsersBySurvey(Survey survey);
    ResponseModel<List<SurveyUser>> getParticipatedSurveys(User user);
    ResponseModel<SurveyUser> participateSurvey(Integer surveyId, Integer userId);

    ResponseModel<List<Survey>> getNotParticipatedSurveys(User user);

}
