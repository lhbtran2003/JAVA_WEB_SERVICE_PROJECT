package com.example.internship.service.assessmentround;

import com.example.internship.dto.request.assessmentround.AddAssessmentRound;
import com.example.internship.dto.request.assessmentround.UpdateAssessmentRound;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.AssessmentRound;

import java.util.List;
import java.util.Optional;

public interface IAssessmentRoundService {
    ApiResponse<List<AssessmentRound>> findAll();
    ApiResponse<AssessmentRound> findById(Integer id);
    ApiResponse<List<AssessmentRound>> findByInternshipPhase(String phaseName);
    ApiResponse<AssessmentRound> create(AddAssessmentRound request);
    ApiResponse<AssessmentRound> update(Integer id, UpdateAssessmentRound request);
    ApiResponse<Void> delete(Integer id);

}
