package com.zekiyetekin.surveyhub.service.implementation;

import com.zekiyetekin.surveyhub.dto.SurveyDto;
import com.zekiyetekin.surveyhub.entity.Option;
import com.zekiyetekin.surveyhub.entity.Question;
import com.zekiyetekin.surveyhub.entity.ResponseModel;
import com.zekiyetekin.surveyhub.entity.Survey;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseMessageEnum;
import com.zekiyetekin.surveyhub.enumuration.responsemodel.ResponseStatusEnum;
import com.zekiyetekin.surveyhub.filter.SurveyFilter;
import com.zekiyetekin.surveyhub.mapper.SurveyMapper;
import com.zekiyetekin.surveyhub.repository.OptionRepository;
import com.zekiyetekin.surveyhub.repository.QuestionRepository;
import com.zekiyetekin.surveyhub.repository.SurveyRepository;
import com.zekiyetekin.surveyhub.service.SurveyService;
import com.zekiyetekin.surveyhub.specification.SurveySpecification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final OptionRepository optionRepository;
    private final SurveyMapper surveyMapper;
    public SurveyServiceImpl(SurveyRepository surveyRepository,
                             QuestionRepository questionRepository,
                             OptionRepository optionRepository,
                             SurveyMapper surveyMapper){
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
        this.surveyMapper = surveyMapper;
    }

    public ResponseModel<List<SurveyDto>> allList(){
        List<Survey> surveyList = surveyRepository.findAll();
        if (surveyList.isEmpty()){
            return new ResponseModel<>(ResponseStatusEnum.NOT_FOUND.getCode(), ResponseStatusEnum.NOT_FOUND.getMessage(), false, ResponseMessageEnum.DATA_NOT_FOUND, null);
        }
        return new ResponseModel<>(ResponseStatusEnum.OK.getCode(), ResponseStatusEnum.OK.getMessage(), true, ResponseMessageEnum.LISTING_SUCCESSFULLY_DONE, surveyMapper.convertList(surveyList));
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
            Survey newSurvey = new Survey();
            newSurvey.setName(survey.getName());
            newSurvey.setDescription(survey.getDescription());
            newSurvey.setCategory(survey.getCategory());
            newSurvey.setVisibility(true);
            newSurvey.setCreatedAt(LocalDate.now());
            newSurvey.setDeadlineDate(survey.getDeadlineDate());
            newSurvey.setQuestionCount(survey.getQuestionCount());
            newSurvey.setUser(survey.getUser());
            newSurvey = surveyRepository.save(newSurvey);

            for (Question question : survey.getQuestions()) {
                Question newQuestion = new Question();
                newQuestion.setContent(question.getContent());
                newQuestion.setType(question.getType());
                newQuestion.setRequired(true);
                newQuestion.setSurvey(newSurvey);
                newQuestion = questionRepository.save(newQuestion);
                questionRepository.flush();

                for (Option option : question.getOptions()) {
                    Option newOption = new Option();
                    newOption.setContent(option.getContent());
                    newOption.setQuestion(newQuestion);
                    optionRepository.save(newOption);
                    optionRepository.flush();
                }
            }
        return new ResponseModel<>(ResponseStatusEnum.CREATED.getCode(), ResponseStatusEnum.CREATED.getMessage(), true, ResponseMessageEnum.CREATED_SUCCESSFULLY, newSurvey);
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
