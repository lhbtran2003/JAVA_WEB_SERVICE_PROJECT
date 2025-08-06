package com.example.internship.controller;

import com.example.internship.dto.request.user.AddUserRequest;
import com.example.internship.dto.request.user.UpdateUserRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.User;
import com.example.internship.service.user.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getUsers(@RequestParam(required = false, defaultValue = "") String role) {
        return new ResponseEntity<>(userService.getAllUsers(role), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@Valid @RequestBody AddUserRequest request){
        return new ResponseEntity<>(userService.createUser(request),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable int id, @Valid @RequestBody UpdateUserRequest request){
        return new ResponseEntity<>(userService.updateUser(id, request),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
