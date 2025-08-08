package com.example.internship.mapper;

import com.example.internship.dto.request.evaluationcriteria.AddEvaluationRequest;
import com.example.internship.dto.request.evaluationcriteria.UpdateEvaluationRequest;
import com.example.internship.entity.EvaluationCriteria;

public class EvaluationMapper {
    public static EvaluationCriteria toEntity(AddEvaluationRequest request) {
        EvaluationCriteria evaluationCriteria = new EvaluationCriteria();
        evaluationCriteria.setCriterionName(request.getCriterionName());
        evaluationCriteria.setDescription(request.getDescription());
        evaluationCriteria.setMaxScore(request.getMaxScore());
        return evaluationCriteria;
    }

    public static EvaluationCriteria toEntity(EvaluationCriteria evaluationCriteriaExisted, UpdateEvaluationRequest request) {
        evaluationCriteriaExisted.setMaxScore(request.getMaxScore());
        evaluationCriteriaExisted.setCriterionName(request.getCriterionName());
        evaluationCriteriaExisted.setDescription(request.getDescription());
        return evaluationCriteriaExisted;
    }
}
