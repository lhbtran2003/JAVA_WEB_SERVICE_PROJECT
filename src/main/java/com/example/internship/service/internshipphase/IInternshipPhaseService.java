package com.example.internship.service.internshipphase;

import com.example.internship.dto.request.internshipphase.AddPhaseRequest;
import com.example.internship.dto.request.internshipphase.UpdatePhaseRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.InternshipPhase;

import java.util.List;

public interface IInternshipPhaseService {
    ApiResponse<List<InternshipPhase>> findAllInternshipPhases();
    ApiResponse<InternshipPhase> findById (Integer internshipPhaseId);
    ApiResponse<InternshipPhase> createInternshipPhase(AddPhaseRequest  addPhaseRequest);
    ApiResponse<InternshipPhase> updateInternshipPhase(Integer internshipPhaseId, UpdatePhaseRequest request);
    ApiResponse<Void> deleteInternshipPhase(Integer internshipPhaseId);
}
