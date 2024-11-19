package com.zekiyetekin.surveyhub.service.implementation;

import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.Survey;
import com.zekiyetekin.surveyhub.entity.SurveyUser;
import com.zekiyetekin.surveyhub.entity.User;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseMessageEnum;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseStatusEnum;
import com.zekiyetekin.surveyhub.repository.SurveyRepository;
import com.zekiyetekin.surveyhub.repository.SurveyUserRepository;
import com.zekiyetekin.surveyhub.repository.UserRepository;
import com.zekiyetekin.surveyhub.service.SurveyUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SurveyUserServiceImpl implements SurveyUserService {

    private final SurveyUserRepository surveyUserRepository;
    private final UserRepository userRepository;
    private  final SurveyRepository surveyRepository;
    public SurveyUserServiceImpl(
            SurveyUserRepository surveyUserRepository,
            UserRepository userRepository,
            SurveyRepository surveyRepository){
        this.surveyUserRepository = surveyUserRepository;
        this.userRepository = userRepository;
        this.surveyRepository = surveyRepository;
    }
    public ResponseModel<List<SurveyUser>> getParticipatedSurveys(User user)
    {
        List<SurveyUser> surveyUserList = surveyUserRepository.findSurveyUsersByUser(user);
        return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, surveyUserList);
    }

    public ResponseModel<SurveyUser> participateSurvey(Integer surveyId, Integer userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new RuntimeException("Survey not found"));

        SurveyUser alreadySurveyUser = surveyUserRepository.findSurveyUsersBySurveyAndUser(survey,user);

        if (alreadySurveyUser != null) {
            return new ResponseModel<>(ResponseStatusEnum.FOUND.getCode(), ResponseStatusEnum.FOUND.getMessage(), true, ResponseMessageEnum.ALREADY_EXIST, alreadySurveyUser);
        }
        SurveyUser newSurveyUser = new SurveyUser();
        newSurveyUser.setSurvey(survey);
        newSurveyUser.setUser(user);
        newSurveyUser.setParticipationDate(LocalDateTime.now());
        surveyUserRepository.save(newSurveyUser);

        return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.SUCCESSFULLY_DONE, newSurveyUser);

    }

}
