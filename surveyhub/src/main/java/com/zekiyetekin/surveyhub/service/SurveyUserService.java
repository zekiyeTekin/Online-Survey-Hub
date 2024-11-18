package com.zekiyetekin.surveyhub.service;

import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.SurveyUser;
import com.zekiyetekin.surveyhub.entity.User;

import java.util.List;

public interface SurveyUserService {


    ResponseModel<List<SurveyUser>> getParticipatedSurveys(User user);
}
