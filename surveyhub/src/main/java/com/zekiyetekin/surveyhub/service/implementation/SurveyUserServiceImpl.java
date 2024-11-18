package com.zekiyetekin.surveyhub.service.implementation;

import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.SurveyUser;
import com.zekiyetekin.surveyhub.entity.User;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseMessageEnum;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseStatusEnum;
import com.zekiyetekin.surveyhub.repository.SurveyUserRepository;
import com.zekiyetekin.surveyhub.service.SurveyUserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyUserServiceImpl implements SurveyUserService {

    private final SurveyUserRepository surveyUserRepository;
    public SurveyUserServiceImpl(SurveyUserRepository surveyUserRepository){
        this.surveyUserRepository = surveyUserRepository;
    }
    public ResponseModel<List<SurveyUser>> getParticipatedSurveys(User user)
    {
        List<SurveyUser> surveyUserList = surveyUserRepository.findSurveyUsersByUser(user);
        return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, surveyUserList);

    }

}
