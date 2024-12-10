package com.zekiyetekin.surveyhub.service.implementation;

import com.zekiyetekin.surveyhub.dto.SurveyDto;
import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.Survey;
import com.zekiyetekin.surveyhub.entity.User;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseMessageEnum;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseStatusEnum;
import com.zekiyetekin.surveyhub.mapper.SurveyMapper;
import com.zekiyetekin.surveyhub.repository.SurveyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SurveyTest {
    @InjectMocks
    private SurveyServiceImpl surveyService;
    @Mock
    private SurveyRepository surveyRepository;
    @Mock
    private SurveyMapper surveyMapper;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllList_Success(){
        //Arrange:
        Survey survey = new Survey();
        List<Survey> surveyList = Arrays.asList(survey);
        List<SurveyDto> surveyDtoList = Arrays.asList(new SurveyDto());

        //Act:
        when(surveyRepository.findAll()).thenReturn(surveyList);
        when(surveyMapper.convertList(surveyList)).thenReturn(surveyDtoList);

        ResponseModel<List<SurveyDto>> response = surveyService.allList();

        //Assert:
        assertEquals(ResponseStatusEnum.OK.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, response.getMessage());
        assertTrue(response.getSuccess());
        assertEquals(surveyDtoList, response.getData());
    }

    @Test
    void testAllList_Exception(){
        // Arrange
        List<Survey> emptySurveyList = Collections.emptyList();

        when(surveyRepository.findAll()).thenReturn(emptySurveyList);

        ResponseModel<List<SurveyDto>> response = surveyService.allList();

        //Assert:
        assertEquals(ResponseStatusEnum.NOT_FOUND.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.DATA_NOT_FOUND, response.getMessage());
        assertFalse(response.getSuccess());
        assertNull(response.getData());
    }

    @Test
    void testGetSurveysByUser_Success(){
        //Arrange
        Integer userId = 1;
        User user = new User();
        user.setId(userId);

        Survey survey = new Survey();
        survey.setId(1);
        survey.setUser(user);

        List<Survey> surveyList = new ArrayList<>();
        surveyList.add(survey);

        List<SurveyDto> surveyDtoList = new ArrayList<>();

        when(surveyRepository.findSurveysByUser_Id(userId)).thenReturn(surveyList);
        when(surveyMapper.convertList(surveyList)).thenReturn(surveyDtoList);

        // Act:
        ResponseModel<List<SurveyDto>> response = surveyService.getSurveysByUser(userId);

        // Assert:
        assertEquals(ResponseStatusEnum.OK.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, response.getMessage());
        assertTrue(response.getSuccess());
        assertEquals(surveyDtoList, response.getData());

        verify(surveyRepository, times(1)).findSurveysByUser_Id(userId);
        verify(surveyMapper,times(1)).convertList(surveyList);
    }

    @Test
    void testGetSurveysByUser_Exception(){
        Integer userId = 1;

        when(surveyRepository.findSurveysByUser_Id(userId)).thenReturn(new ArrayList<>());

        ResponseModel<List<SurveyDto>> response = surveyService.getSurveysByUser(userId);

        assertEquals(ResponseStatusEnum.NOT_FOUND.getCode(), response.getCode());
        assertEquals(ResponseMessageEnum.DATA_NOT_FOUND, response.getMessage());
        assertFalse(response.getSuccess());
        assertNull(response.getData());

        verify(surveyRepository, times(1)).findSurveysByUser_Id(userId);
        verify(surveyMapper,times(0)).convertList(any());
    }

}
