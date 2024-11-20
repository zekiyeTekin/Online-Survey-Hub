package com.zekiyetekin.surveyhub.controller;

import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.Survey;
import com.zekiyetekin.surveyhub.entity.SurveyUser;
import com.zekiyetekin.surveyhub.entity.User;
import com.zekiyetekin.surveyhub.service.SurveyService;
import com.zekiyetekin.surveyhub.service.SurveyUserService;
import com.zekiyetekin.surveyhub.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey-user")
public class SurveyUserController {

    private final SurveyUserService surveyUserService;
    private final UserService userService;
    private final SurveyService surveyService;

    public SurveyUserController(SurveyUserService surveyUserService, UserService userService, SurveyService surveyService){
        this.surveyUserService = surveyUserService;
        this.userService = userService;
        this.surveyService = surveyService;
    }

    //specific survey get participate users
    @GetMapping("/usersBySurvey")
    public ResponseModel<List<SurveyUser>> getUsersBySurvey(@RequestParam Integer surveyId){
        Survey survey = surveyService.getSurveysById(surveyId).getData();
        return surveyUserService.getUsersBySurvey(survey);
    }


    // specific user get participated surveys
    @GetMapping("/participated")
    public ResponseModel<List<SurveyUser>> getParticipatedSurveys(@RequestParam Integer userId){
        User user = userService.getUserById(userId).getData();
        return surveyUserService.getParticipatedSurveys(user);
    }

    // created survey
    @PostMapping("/participate")
    public ResponseModel<SurveyUser> participateSurvey(@RequestParam Integer surveyId, @RequestParam Integer userId){
        return  surveyUserService.participateSurvey(surveyId, userId);
    }

    @GetMapping("/not-participated")
    public ResponseModel<List<Survey>> getNotParticipatedSurveys(@RequestParam Integer userId){
        User user = userService.getUserById(userId).getData();
        return surveyUserService.getNotParticipatedSurveys(user);
    }






}
