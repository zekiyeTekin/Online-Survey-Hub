package com.zekiyetekin.surveyhub.specification;

import com.zekiyetekin.surveyhub.entity.Survey;
import com.zekiyetekin.surveyhub.filter.SurveyFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SurveySpecification {

    private SurveySpecification(){
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    public static Specification<Survey> searchByDate(SurveyFilter surveyFilter){
        return (Root< Survey > root,CriteriaQuery < ?> query, CriteriaBuilder builder) ->{
            List<Predicate> predicateList = new ArrayList<>();

            if (surveyFilter.getCreatedAt() != null) {
                predicateList.add(builder.equal(root.get("createdAt"), surveyFilter.getCreatedAt()));
            }
            return builder.and(predicateList.toArray(predicateList.toArray(new Predicate[0])));
        };
    }

    public static Specification<Survey> searchByCategory(SurveyFilter surveyFilter){
        return (Root< Survey > root, CriteriaQuery < ?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if(surveyFilter.getCategory() != null){
                predicateList.add(builder.equal(root.get("category"), surveyFilter.getCategory()));
            }
            return builder.and(predicateList.toArray(predicateList.toArray(new Predicate[0])));

        };
    }
}
