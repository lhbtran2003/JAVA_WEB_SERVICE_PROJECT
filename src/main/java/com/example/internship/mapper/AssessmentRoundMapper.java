package com.example.internship.mapper;

import com.example.internship.dto.request.assessmentround.AddAssessmentRound;
import com.example.internship.dto.request.assessmentround.UpdateAssessmentRound;
import com.example.internship.entity.AssessmentRound;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AssessmentRoundMapper {
    public static AssessmentRound toEntity (AddAssessmentRound request) {
        AssessmentRound entity = new AssessmentRound();
        entity.setDescription(request.getDescription());
        entity.setRoundName(request.getRoundName());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        entity.setEndDate(LocalDate.parse(request.getEndDate(), dtf));
        entity.setStartDate(LocalDate.parse(request.getStartDate(), dtf));
        return entity;
    }

    public static AssessmentRound toEntity(AssessmentRound assessmentRoundExisted,UpdateAssessmentRound request) {
        assessmentRoundExisted.setDescription(request.getDescription());
        assessmentRoundExisted.setRoundName(request.getRoundName());

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assessmentRoundExisted.setEndDate(LocalDate.parse(request.getEndDate(), dtf));
        assessmentRoundExisted.setStartDate(LocalDate.parse(request.getStartDate(), dtf));
        return assessmentRoundExisted;
    }
}
