package com.zekiyetekin.surveyhub.controller;

import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.Survey;
import com.zekiyetekin.surveyhub.service.SurveyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;
    public SurveyController(SurveyService surveyService){
        this.surveyService = surveyService;
    }

    @GetMapping("/surveys")
    public ResponseModel<List<Survey>> getSurveysByUser(@RequestParam Integer userId){
        return surveyService.getSurveysByUser(userId);
    }

    @PostMapping("/create")
    public ResponseModel<Survey> create(@RequestBody Survey survey){
        return surveyService.create(survey);
    }
}
