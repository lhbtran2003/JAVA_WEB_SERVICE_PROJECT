package com.example.internship.controller;

import com.example.internship.config.security.principle.UserDetail;
import com.example.internship.dto.request.internshipassignment.AddInternshipAssignment;
import com.example.internship.dto.request.internshipassignment.UpdateInternshipAssignment;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.InternshipAssignment;
import com.example.internship.service.internshipassignment.IInternshipAssignmentService;
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
@RequiredArgsConstructor
@RequestMapping("/api/internship_assignments")
public class InternshipAssignmentController {
    private final IInternshipAssignmentService internshipAssignmentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InternshipAssignment>>> findAll(@AuthenticationPrincipal UserDetail userDetail) {
        Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
        int id = userDetail.getId();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_MENTOR"))) {
            return new ResponseEntity<>(internshipAssignmentService.findAllByMentor(id), HttpStatus.OK);
        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_STUDENT"))) {
            return new ResponseEntity<>(internshipAssignmentService.findAllByStudent(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(internshipAssignmentService.findAllByAdmin(), HttpStatus.OK);
    }

    @GetMapping("/{assignment_id}")
    public ResponseEntity<ApiResponse<InternshipAssignment>> findById(@PathVariable Integer assignment_id, @AuthenticationPrincipal UserDetail userDetail) {
        Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
        int id = userDetail.getId();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_MENTOR"))) {
            return new ResponseEntity<>(internshipAssignmentService.findByIdByMentor(assignment_id,id), HttpStatus.OK);
        } else if (authorities.contains(new SimpleGrantedAuthority("ROLE_STUDENT"))) {
            return new ResponseEntity<>(internshipAssignmentService.findByIdByStudent(assignment_id,id), HttpStatus.OK);
        }
        return new ResponseEntity<>(internshipAssignmentService.findById(assignment_id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<InternshipAssignment>> create (@Valid @RequestBody AddInternshipAssignment request) {
        return new ResponseEntity<>(internshipAssignmentService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{assignment_id}")
    public ResponseEntity<ApiResponse<InternshipAssignment>>  update(@PathVariable Integer assignment_id, @Valid @RequestBody UpdateInternshipAssignment request) {
        return new ResponseEntity<>(internshipAssignmentService.update(assignment_id,request),HttpStatus.OK);
    }

}
