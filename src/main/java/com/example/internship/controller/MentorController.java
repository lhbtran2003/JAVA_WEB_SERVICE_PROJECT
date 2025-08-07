package com.example.internship.controller;

import com.example.internship.config.security.principle.UserDetail;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.Mentor;
import com.example.internship.service.mentor.IMentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/mentors")
@RequiredArgsConstructor
public class MentorController {

    private final IMentorService mentorService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Mentor>>> getAllMentors(@AuthenticationPrincipal UserDetail userDetail) {
        Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return new ResponseEntity<>(mentorService.getAllMentorsForAdmin(), HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Mentor>> getMentorById(@PathVariable Integer id, @AuthenticationPrincipal UserDetail userDetail) {
        Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_MENTOR"))
                && Objects.equals(id, userDetail.getId())) {
            return new ResponseEntity<>(mentorService.getMentorById(id), HttpStatus.OK);
        }
        return null;
    }
}
