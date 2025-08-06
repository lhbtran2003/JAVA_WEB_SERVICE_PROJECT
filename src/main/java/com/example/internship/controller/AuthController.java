package com.example.internship.controller;

import com.example.internship.dto.request.FormLogin;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.User;
import com.example.internship.service.user.IUserService;
import com.example.internship.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login (@RequestBody FormLogin request) {
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<User>> getUserInformation() {
      return new ResponseEntity<>(userService.getUserInformation(), HttpStatus.OK);
    }
}
