package com.example.internship.service.assessmentresutl;

import com.example.internship.dto.request.assessmentresult.AddAssessmentResult;
import com.example.internship.dto.request.assessmentresult.UpdateAssessmentResult;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.AssessmentResults;

import java.util.List;

public interface IAssessmentResultService {
    ApiResponse<List<AssessmentResults>> findAll();
    ApiResponse<List<AssessmentResults>> findAllByMentor(Integer idLogin);
    ApiResponse<List<AssessmentResults>> findAllByStudent(Integer idLogin);
    ApiResponse<AssessmentResults> create (AddAssessmentResult request, Integer idLogin);
    ApiResponse<AssessmentResults> update (UpdateAssessmentResult request,Integer assessmentResultId, Integer idLogin);
}
