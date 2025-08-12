package com.example.internship.controller;

import com.example.internship.config.security.principle.UserDetail;
import com.example.internship.dto.request.mentor.AddMentorRequest;
import com.example.internship.dto.request.mentor.UpdateMentorRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.Mentor;
import com.example.internship.service.mentor.IMentorService;
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
import java.util.Objects;

@RestController
@RequestMapping("/api/mentors")
@RequiredArgsConstructor
public class MentorController {

    private final IMentorService mentorService;

    @GetMapping
    public ResponseEntity<?> getAllMentors(@AuthenticationPrincipal UserDetail userDetail) {
        Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_STUDENT"))) {
            return new ResponseEntity<>(mentorService.getAllMentorsForStudent(), HttpStatus.OK);
        }
        return new ResponseEntity<>(mentorService.getAllMentors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Mentor>> getMentorById(@PathVariable Integer id, @AuthenticationPrincipal UserDetail userDetail) {
        Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_MENTOR"))) {
            return new ResponseEntity<>(mentorService.getMentorByIdForMentor(id,userDetail.getId()), HttpStatus.OK);
        }
        return new ResponseEntity<>(mentorService.getMentorById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Mentor>> createMentor(@Valid @RequestBody AddMentorRequest request) {
        return new ResponseEntity<>(mentorService.createMentor(request), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Mentor>> updateMentor(@PathVariable Integer id,
                                                            @RequestBody UpdateMentorRequest request,
                                                            @AuthenticationPrincipal UserDetail userDetail
    ) {
        Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_MENTOR"))) {
            return new ResponseEntity<>(mentorService.updateMentorByMentor(id,userDetail.getId(),request), HttpStatus.OK);
        }
        return new ResponseEntity<>(mentorService.updateMentor(id,request), HttpStatus.OK);

    }
}
