package com.zekiyetekin.surveyhub.repository;

import com.zekiyetekin.surveyhub.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Integer> {

    List<Survey> findSurveysByUser_Id(Integer userId);
}
