package com.zekiyetekin.surveyhub.service.implementation;

import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.Survey;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseMessageEnum;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseStatusEnum;
import com.zekiyetekin.surveyhub.filter.SurveyFilter;
import com.zekiyetekin.surveyhub.repository.SurveyRepository;
import com.zekiyetekin.surveyhub.service.SurveyService;
import com.zekiyetekin.surveyhub.specification.SurveySpecification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    public SurveyServiceImpl(SurveyRepository surveyRepository){
        this.surveyRepository = surveyRepository;
    }

    public ResponseModel<List<Survey>> allList(){
        List<Survey> surveyList = surveyRepository.findAll();
        if (surveyList.isEmpty()){
            return new ResponseModel<>(ResponseStatusEnum.NOT_FOUND.getCode(), ResponseStatusEnum.NOT_FOUND.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
        return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, surveyList);
    }

    public ResponseModel<List<Survey>> getSurveysByUser(Integer userId){
        List<Survey> surveyList = surveyRepository.findSurveysByUser_Id(userId);
        if (surveyList.isEmpty()){
            return new ResponseModel<>(ResponseStatusEnum.NOT_FOUND.getCode(), ResponseStatusEnum.NOT_FOUND.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
        return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, surveyList);
    }

    public  ResponseModel<Survey> create(Survey survey){
        try{
            survey.setCreatedAt(LocalDate.now());
            surveyRepository.save(survey);
            return new ResponseModel<>(ResponseStatusEnum.CREATED.getCode(), ResponseStatusEnum.CREATED.getMessage(), true, ResponseMessageEnum.CREATED_SUCCESSFULLY, survey);
        }catch (Exception e){
            return new ResponseModel<>(ResponseStatusEnum.INTERNAL_SERVER_ERROR.getCode(), ResponseStatusEnum.INTERNAL_SERVER_ERROR.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
    }

    public ResponseModel<Survey> getSurveysById(Integer surveyId){
        Survey findedSurvey = surveyRepository.findById(surveyId).orElse(null);
        if(findedSurvey == null){
            return new ResponseModel<>(ResponseStatusEnum.NOT_FOUND.getCode(), ResponseStatusEnum.NOT_FOUND.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
        return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.SUCCESSFULLY_DONE, findedSurvey );
    }

    public ResponseModel<List<Survey>> searchByDateWithFilter(SurveyFilter surveyFilter) {
        List<Survey> surveyFilterList = surveyRepository.findAll(SurveySpecification.searchByDate(surveyFilter));
        if (surveyFilterList.isEmpty()){
            return new ResponseModel<>(ResponseStatusEnum.NOT_FOUND.getCode(), ResponseStatusEnum.NOT_FOUND.getMessage(), false, ResponseMessageEnum.SEARCHED_ERROR, null);
        }
        return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.SEARCHED_SUCCESSFULLY, surveyFilterList);
    }

    public ResponseModel<List<Survey>> searchByCategoryWithFilter(SurveyFilter surveyFilter){
        List<Survey> surveyFilterList = surveyRepository.findAll(SurveySpecification.searchByCategory(surveyFilter));
        if (surveyFilterList.isEmpty()){
            return new ResponseModel<>(ResponseStatusEnum.NOT_FOUND.getCode(), ResponseStatusEnum.NOT_FOUND.getMessage(), false, ResponseMessageEnum.SEARCHED_ERROR, null);
        }
        return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.SEARCHED_SUCCESSFULLY, surveyFilterList);
    }
}
