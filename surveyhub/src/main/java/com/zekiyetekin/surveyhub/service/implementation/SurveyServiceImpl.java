package com.zekiyetekin.surveyhub.service.implementation;

import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.Survey;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseMessageEnum;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseStatusEnum;
import com.zekiyetekin.surveyhub.repository.SurveyRepository;
import com.zekiyetekin.surveyhub.service.SurveyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    public SurveyServiceImpl(SurveyRepository surveyRepository){
        this.surveyRepository = surveyRepository;
    }

    public ResponseModel<List<Survey>> getSurveysByUser(Integer userId){
        List<Survey> surveyList = surveyRepository.findSurveysByUser_Id(userId);
        if (surveyList.isEmpty()){
            return new ResponseModel<>(ResponseStatusEnum.NOT_FOUND.getCode(), ResponseStatusEnum.NOT_FOUND.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
        return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, surveyList);


    }
}
