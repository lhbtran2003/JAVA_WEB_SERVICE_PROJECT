package com.example.internship.mapper;

import com.example.internship.dto.request.assessmentresult.AddAssessmentResult;
import com.example.internship.dto.request.assessmentresult.UpdateAssessmentResult;
import com.example.internship.entity.AssessmentResults;

public class AssessmentResultMapper {
    public static AssessmentResults toEntity (AddAssessmentResult request) {
        AssessmentResults entity = new AssessmentResults();
        entity.setComments(request.getComments());
        entity.setScore(request.getScore());
        return entity;
    }

    public static AssessmentResults toEntity (AssessmentResults assessmentResultExisted,UpdateAssessmentResult request) {
        assessmentResultExisted.setComments(request.getComments());
        assessmentResultExisted.setScore(request.getScore());
        return assessmentResultExisted;
    }
}
