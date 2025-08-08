package com.example.internship.service.evaluationcriteria;

import com.example.internship.dto.request.evaluationcriteria.AddEvaluationRequest;
import com.example.internship.dto.request.evaluationcriteria.UpdateEvaluationRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.EvaluationCriteria;

import java.util.List;

public interface IEvaluationCriteriaService {
    ApiResponse<List<EvaluationCriteria>> findAllEvaluationCriterias();
    ApiResponse<EvaluationCriteria> findById (Integer id);
    ApiResponse<EvaluationCriteria> createEvaluationCriteria(AddEvaluationRequest request);
    ApiResponse<EvaluationCriteria> updateEvaluationCriteria(Integer internshipPhaseId, UpdateEvaluationRequest request);
    ApiResponse<Void> deleteEvaluationCriteria(Integer id);
}
