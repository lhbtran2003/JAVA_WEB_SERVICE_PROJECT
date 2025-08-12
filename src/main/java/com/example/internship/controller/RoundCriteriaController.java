package com.example.internship.controller;

import com.example.internship.dto.request.roundcriteria.AddRoundCriteria;
import com.example.internship.dto.request.roundcriteria.UpdateRoundCriteria;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.RoundCriteria;
import com.example.internship.service.roundcriteria.IRoundCriteriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/round_criteria")
@RequiredArgsConstructor
public class RoundCriteriaController {
    private final IRoundCriteriaService roundCriteriaService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<RoundCriteria>>> findAll() {
        return new ResponseEntity<>(roundCriteriaService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{round_criterion_id}")
    public ResponseEntity<ApiResponse<RoundCriteria>> findById(@PathVariable Integer round_criterion_id) {
        return new ResponseEntity<>(roundCriteriaService.findById(round_criterion_id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<RoundCriteria>> create(@Valid @RequestBody AddRoundCriteria request) {
        return new ResponseEntity<>(roundCriteriaService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{round_criterion_id}")
    public ResponseEntity<ApiResponse<RoundCriteria>> update (@PathVariable Integer round_criterion_id, @Valid @RequestBody UpdateRoundCriteria request) {
        return new ResponseEntity<>(roundCriteriaService.update(round_criterion_id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{round_criterion_id}")
    public ResponseEntity<ApiResponse<Void>> deleteById(@PathVariable Integer round_criterion_id) {
        return new ResponseEntity<>(roundCriteriaService.deleteById(round_criterion_id), HttpStatus.OK);
    }
}
