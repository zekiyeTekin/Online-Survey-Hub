package com.zekiyetekin.surveyhub.service.implementation;

import com.zekiyetekin.surveyhub.dto.SurveyUserDto;
import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.Survey;
import com.zekiyetekin.surveyhub.entity.SurveyUser;
import com.zekiyetekin.surveyhub.entity.User;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseMessageEnum;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseStatusEnum;
import com.zekiyetekin.surveyhub.mapper.SurveyUserMapper;
import com.zekiyetekin.surveyhub.repository.SurveyUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SurveyUserTest {

    @InjectMocks
    private SurveyUserServiceImpl surveyUserService;
    @Mock
    private SurveyUserRepository surveyUserRepository;
    @Mock
    private SurveyUserMapper surveyUserMapper;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUsersBySurvey_Success(){
        Survey survey = new Survey();
        survey.setId(1);

        SurveyUser surveyUser = new SurveyUser();
        surveyUser.setId(1);

        List<SurveyUser> surveyUserList = List.of(surveyUser);
        List<SurveyUserDto> surveyUserDtoList = List.of(new SurveyUserDto());

        when(surveyUserRepository.findSurveyUsersBySurvey(survey)).thenReturn(surveyUserList);
        when(surveyUserMapper.convertList(surveyUserList)).thenReturn(surveyUserDtoList);

        ResponseModel<List<SurveyUserDto>> response = surveyUserService.getUsersBySurvey(survey);

        assertEquals(ResponseStatusEnum.OK.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, response.getMessage());
        assertTrue(response.getSuccess());
        assertNotNull(response.getData());
        assertEquals(1, response.getData().size());

        verify(surveyUserRepository, times(1)).findSurveyUsersBySurvey(survey);
        verify(surveyUserMapper, times(1)).convertList(surveyUserList);
    }

    @Test
    void testGetParticipatedSurveys(){
        User user = new User();
        user.setId(1);

        List<SurveyUser> surveyUserList = new ArrayList<>();
        SurveyUser mockSurveyUser = new SurveyUser();
        mockSurveyUser.setId(1);
        surveyUserList.add(mockSurveyUser);

        List<SurveyUserDto> surveyUserDtoList = new ArrayList<>();
        SurveyUserDto mockSurveyUserDto = new SurveyUserDto();
        surveyUserDtoList.add(mockSurveyUserDto);

        when(surveyUserRepository.findSurveyUsersByUser(user)).thenReturn(surveyUserList);
        when(surveyUserMapper.convertList(surveyUserList)).thenReturn(surveyUserDtoList);

        ResponseModel<List<SurveyUserDto>> response = surveyUserService.getParticipatedSurveys(user);

        assertEquals(ResponseStatusEnum.OK.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, response.getMessage());
        assertTrue(response.getSuccess());
        assertNotNull(response.getData());
        assertEquals(1, response.getData().size());

        verify(surveyUserRepository, times(1)).findSurveyUsersByUser(user);
        verify(surveyUserMapper, times(1)).convertList(surveyUserList);


    }

}
