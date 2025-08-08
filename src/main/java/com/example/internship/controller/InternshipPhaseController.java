package com.example.internship.controller;

import com.example.internship.dto.request.internshipphase.AddPhaseRequest;
import com.example.internship.dto.request.internshipphase.UpdatePhaseRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.InternshipPhase;
import com.example.internship.service.internshipphase.InternshipPhaseImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internship_phases")
@RequiredArgsConstructor
public class InternshipPhaseController {
    private final InternshipPhaseImpl  internshipPhaseImpl;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InternshipPhase>>> getAllInternshipPhases() {
        return new ResponseEntity<>(internshipPhaseImpl.findAllInternshipPhases(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InternshipPhase>> getInternshipPhaseById(@PathVariable Integer id) {
        return new ResponseEntity<>(internshipPhaseImpl.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<InternshipPhase>> createInternshipPhase(@RequestBody AddPhaseRequest request) {
        return new ResponseEntity<>(internshipPhaseImpl.createInternshipPhase(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<InternshipPhase>> updateInternshipPhase(@PathVariable Integer id, @RequestBody UpdatePhaseRequest request) {
        return new ResponseEntity<>(internshipPhaseImpl.updateInternshipPhase(id, request), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteInternshipPhase(@PathVariable Integer id) {
        return new ResponseEntity<>(internshipPhaseImpl.deleteInternshipPhase(id), HttpStatus.OK);
    }
}
