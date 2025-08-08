package com.example.internship.controller;

import com.example.internship.dto.request.evaluationcriteria.AddEvaluationRequest;
import com.example.internship.dto.request.evaluationcriteria.UpdateEvaluationRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.EvaluationCriteria;
import com.example.internship.service.evaluationcriteria.IEvaluationCriteriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluation_criteria")
@RequiredArgsConstructor
public class EvaluationCriteriaController {
    private final IEvaluationCriteriaService evaluationCriteriaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EvaluationCriteria>>> getAllEvaluationCriteria(){
        return new ResponseEntity<>(evaluationCriteriaService.findAllEvaluationCriterias(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EvaluationCriteria>> getEvaluationCriteriaById(@PathVariable Integer id){
        return new ResponseEntity<>(evaluationCriteriaService.findById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<EvaluationCriteria>> createEvaluationCriteria(@RequestBody AddEvaluationRequest request){
        return new ResponseEntity<>(evaluationCriteriaService.createEvaluationCriteria(request),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EvaluationCriteria>> updateEvaluationCriteria(@PathVariable Integer id, @RequestBody UpdateEvaluationRequest request){
        return new ResponseEntity<>(evaluationCriteriaService.updateEvaluationCriteria(id,request),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEvaluationCriteria(@PathVariable Integer id){
        return new ResponseEntity<>(evaluationCriteriaService.deleteEvaluationCriteria(id),HttpStatus.NO_CONTENT);
    }
}
