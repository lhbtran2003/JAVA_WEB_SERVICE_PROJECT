package com.example.internship.controller;

import com.example.internship.config.security.principle.UserDetail;
import com.example.internship.dto.request.assessmentresult.AddAssessmentResult;
import com.example.internship.dto.request.assessmentresult.UpdateAssessmentResult;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.AssessmentResults;
import com.example.internship.service.assessmentresutl.IAssessmentResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/assessment_results")
@RequiredArgsConstructor
public class AssessmentResultController {
    private final IAssessmentResultService assessmentResultService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AssessmentResults>>> getAll() {
        return new ResponseEntity<>(assessmentResultService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AssessmentResults>> create(@Valid @RequestBody AddAssessmentResult request,
                                                                 @AuthenticationPrincipal UserDetail userDetail) {
        int idLogin = userDetail.getId();
        return new ResponseEntity<>(assessmentResultService.create(request, idLogin), HttpStatus.OK);
    }

    @PutMapping("/result_id")
    public ResponseEntity<ApiResponse<AssessmentResults>> update(@Valid @RequestBody UpdateAssessmentResult request,
                                                                 Integer result_id,
                                                                 @AuthenticationPrincipal UserDetail userDetail) {
        int idLogin = userDetail.getId();
        return new ResponseEntity<>(assessmentResultService.update(request, result_id, idLogin), HttpStatus.OK);
    }

}
