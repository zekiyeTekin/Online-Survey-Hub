package com.zekiyetekin.surveyhub.service.implementation;

import com.zekiyetekin.surveyhub.entity.SurveyUser;
import com.zekiyetekin.surveyhub.entity.User;
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


    public List<SurveyUser> getParticipatedSurveys(User user)
    {
        return surveyUserRepository.findSurveyUsersByHasParticipatedTrueAndUser(user);
    }
}
