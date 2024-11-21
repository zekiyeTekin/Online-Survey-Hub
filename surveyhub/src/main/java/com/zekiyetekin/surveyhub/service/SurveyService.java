package com.zekiyetekin.surveyhub.service;

import com.zekiyetekin.surveyhub.dto.SurveyDto;
import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.Survey;
import com.zekiyetekin.surveyhub.filter.SurveyFilter;

import java.util.List;

public interface SurveyService {

    ResponseModel<List<SurveyDto>> allList();
    ResponseModel<List<SurveyDto>> getSurveysByUser(Integer userId);
    ResponseModel<SurveyDto> create(Survey survey);
    ResponseModel<Survey> getSurveysById(Integer surveyId);
    ResponseModel<List<SurveyDto>> searchByDateWithFilter(SurveyFilter surveyFilter);
    ResponseModel<List<SurveyDto>> searchByCategoryWithFilter(SurveyFilter surveyFilter);


}
