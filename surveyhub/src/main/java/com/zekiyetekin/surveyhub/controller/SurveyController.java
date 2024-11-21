package com.zekiyetekin.surveyhub.controller;

import com.zekiyetekin.surveyhub.dto.SurveyDto;
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
    public ResponseModel<List<SurveyDto>> allList(){
        return surveyService.allList();
    }

    @GetMapping("/surveys")
    public ResponseModel<List<SurveyDto>> getSurveysByUser(@RequestParam Integer userId){
        return surveyService.getSurveysByUser(userId);
    }

    @PostMapping("/create")
    public ResponseModel<SurveyDto> create(@RequestBody Survey survey){
        return surveyService.create(survey);
    }

    @GetMapping("/by")
    ResponseModel<Survey> getSurveysById(@RequestBody Integer surveyId){
        return surveyService.getSurveysById(surveyId);
    }

    @PostMapping("/filter/by/date")
    public ResponseModel<List<SurveyDto>> searchByDateWithFilter(@RequestBody SurveyFilter surveyFilter){
        return surveyService.searchByDateWithFilter(surveyFilter);
    }

    @PostMapping("/filter/by/category")
    public ResponseModel<List<SurveyDto>> searchByCategoryWithFilter(@RequestBody SurveyFilter surveyFilter){
        return surveyService.searchByCategoryWithFilter(surveyFilter);
    }




}
