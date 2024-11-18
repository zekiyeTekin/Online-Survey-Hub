package com.zekiyetekin.surveyhub.repository;

import com.zekiyetekin.surveyhub.entity.SurveyUser;
import com.zekiyetekin.surveyhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyUserRepository extends JpaRepository<SurveyUser, Integer> {

    List<SurveyUser> findSurveyUsersByUser(User user);

}
