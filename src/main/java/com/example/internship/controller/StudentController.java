package com.example.internship.controller;

import com.example.internship.config.security.principle.UserDetail;
import com.example.internship.dto.request.student.AddStudentRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.Student;
import com.example.internship.service.student.IStudentService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {


    private final IStudentService studentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Student>>> getAllStudents(@AuthenticationPrincipal  UserDetail userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return new ResponseEntity<>(studentService.getAllStudentsForAdmin(), HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Student>> getStudentById(@PathVariable Integer id, @AuthenticationPrincipal UserDetail userDetails) {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("ROLE_STUDENT"))) {
            return new ResponseEntity<>(studentService.getStudentByIdForStudent(id,userDetails.getId()), HttpStatus.OK);
        }
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> addStudent(@Valid @RequestBody AddStudentRequest request) {
        return new ResponseEntity<>(studentService.createStudent(request), HttpStatus.CREATED);
    }
}
