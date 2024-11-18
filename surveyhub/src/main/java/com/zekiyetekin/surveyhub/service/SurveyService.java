package com.zekiyetekin.surveyhub.service;

import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.Survey;

import java.util.List;

public interface SurveyService {

    ResponseModel<List<Survey>> getSurveysByUser(Integer userId);

    ResponseModel<Survey> create(Survey survey);
}
