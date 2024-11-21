package com.zekiyetekin.surveyhub.controller;

import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.Survey;
import com.zekiyetekin.surveyhub.filter.SurveyFilter;
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

    @GetMapping("/all")
    public ResponseModel<List<Survey>> allList(){
        return surveyService.allList();
    }

    @GetMapping("/surveys")
    public ResponseModel<List<Survey>> getSurveysByUser(@RequestParam Integer userId){
        return surveyService.getSurveysByUser(userId);
    }

    @PostMapping("/create")
    public ResponseModel<Survey> create(@RequestBody Survey survey){
        return surveyService.create(survey);
    }

    @GetMapping("/by")
    ResponseModel<Survey> getSurveysById(Integer surveyId){
        return surveyService.getSurveysById(surveyId);
    }

    @PostMapping("/filter/by/date")
    public ResponseModel<List<Survey>> searchByDateWithFilter(@RequestBody SurveyFilter surveyFilter){
        return surveyService.searchByDateWithFilter(surveyFilter);
    }

    @PostMapping("/filter/by/category")
    public ResponseModel<List<Survey>> searchByCategoryWithFilter(@RequestBody SurveyFilter surveyFilter){
        return surveyService.searchByCategoryWithFilter(surveyFilter);
    }




}
