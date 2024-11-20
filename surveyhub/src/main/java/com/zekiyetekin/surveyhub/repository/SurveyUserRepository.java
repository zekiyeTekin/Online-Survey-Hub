package com.zekiyetekin.surveyhub.repository;

import com.zekiyetekin.surveyhub.entity.Survey;
import com.zekiyetekin.surveyhub.entity.SurveyUser;
import com.zekiyetekin.surveyhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyUserRepository extends JpaRepository<SurveyUser, Integer> {

    List<SurveyUser> findSurveyUsersBySurvey(Survey survey);
    List<SurveyUser> findSurveyUsersByUser(User user);

    @Query("SELECT s FROM Survey s WHERE s.id NOT IN " +
            "(SELECT su.survey.id FROM SurveyUser su WHERE su.user = :user)")
    List<Survey> findSurveysNotParticipatedByUser(@Param("user") User user);
    SurveyUser findSurveyUsersBySurveyAndUser(Survey survey, User user);

}
