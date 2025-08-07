package com.example.internship.service.user;

import com.example.internship.dto.request.user.AddUserRequest;
import com.example.internship.dto.request.FormLogin;
import com.example.internship.dto.request.user.UpdateIsActiveRequest;
import com.example.internship.dto.request.user.UpdateRoleRequest;
import com.example.internship.dto.request.user.UpdateUserRequest;
import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.User;

import java.util.List;

public interface IUserService {
   ApiResponse<String> login(FormLogin request);
   ApiResponse<User> getUserInformation();
   ApiResponse<List<User>> getAllUsers(String role);
   ApiResponse<User> getUserById(Integer id);
   ApiResponse<User> createUser(AddUserRequest request);
   ApiResponse<User> updateUser(Integer id, UpdateUserRequest request);
   ApiResponse<User> changeStatus(Integer id, UpdateIsActiveRequest request);
   ApiResponse<User> changeRole(Integer id, UpdateRoleRequest role);
   void deleteUser(Integer id);
}
