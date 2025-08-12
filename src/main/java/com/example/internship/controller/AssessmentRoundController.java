package com.example.internship.controller;

import com.example.internship.dto.request.assessmentround.AddAssessmentRound;
import com.example.internship.dto.request.assessmentround.UpdateAssessmentRound;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.AssessmentRound;
import com.example.internship.service.assessmentround.IAssessmentRoundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessment_rounds")
@RequiredArgsConstructor
public class AssessmentRoundController {
    private final IAssessmentRoundService assessmentRoundService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AssessmentRound>>> getAllAssessmentRounds(){
//        if(phaseName != null){
//            return ResponseEntity.ok(assessmentRoundService.findByInternshipPhase(phaseName));
//        }
        return new ResponseEntity<>(assessmentRoundService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AssessmentRound>> getAssessmentRoundById(@PathVariable Integer id){
        return ResponseEntity.ok(assessmentRoundService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AssessmentRound>> createAssessmentRound(@Valid @RequestBody AddAssessmentRound request){
        return new ResponseEntity<>(assessmentRoundService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AssessmentRound>> updateAssessmentRound(@PathVariable Integer id,@Valid @RequestBody UpdateAssessmentRound request){
        return new ResponseEntity<>(assessmentRoundService.update(id,request),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAssessmentRound(@PathVariable Integer id){
        return new ResponseEntity<>(assessmentRoundService.delete(id), HttpStatus.OK);
    }

}
