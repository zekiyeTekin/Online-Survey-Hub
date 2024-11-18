package com.zekiyetekin.surveyhub.controller;

import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.SurveyUser;
import com.zekiyetekin.surveyhub.entity.User;
import com.zekiyetekin.surveyhub.service.SurveyUserService;
import com.zekiyetekin.surveyhub.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/survey-user")
public class SurveyUserController {

    private final SurveyUserService surveyUserService;
    private final UserService userService;

    public SurveyUserController(SurveyUserService surveyUserService, UserService userService){
        this.surveyUserService = surveyUserService;
        this.userService = userService;
    }

    @GetMapping("/participated")
    public ResponseModel<List<SurveyUser>> getParticipatedSurveys(@RequestParam Integer userId){
        User user = userService.getUserById(userId).getData();
        return surveyUserService.getParticipatedSurveys(user);
    }




}
