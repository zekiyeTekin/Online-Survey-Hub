package com.zekiyetekin.surveyhub.service;

import com.zekiyetekin.surveyhub.dto.SurveyDto;
import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.Survey;
import com.zekiyetekin.surveyhub.filter.SurveyFilter;

import java.util.List;

public interface SurveyService {

    ResponseModel<List<SurveyDto>> allList();
    ResponseModel<List<Survey>> getSurveysByUser(Integer userId);
    ResponseModel<Survey> create(Survey survey);
    ResponseModel<Survey> getSurveysById(Integer surveyId);
    ResponseModel<List<Survey>> searchByDateWithFilter(SurveyFilter surveyFilter);
    ResponseModel<List<Survey>> searchByCategoryWithFilter(SurveyFilter surveyFilter);


}
