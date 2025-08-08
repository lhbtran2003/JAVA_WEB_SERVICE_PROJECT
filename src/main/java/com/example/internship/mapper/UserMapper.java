package com.example.internship.mapper;

import com.example.internship.dto.request.student.AddStudentRequest;
import com.example.internship.dto.request.user.AddUserRequest;
import com.example.internship.dto.request.user.UpdateUserRequest;
import com.example.internship.entity.RoleName;
import com.example.internship.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static User toEntity(AddUserRequest request, PasswordEncoder passwordEncoder){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        RoleName roleName = null;
        switch (request.getRole()) {
            case "student":
                roleName = RoleName.ROLE_STUDENT;
                break;
            case "mentor":
                roleName = RoleName.ROLE_MENTOR;
                break;
            case "admin":
                roleName = RoleName.ROLE_ADMIN;
                break;
            default:
                break;
        }
        user.setRole(roleName);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        return user;
    }

    public static User toEntity(UpdateUserRequest request, User userExisted, PasswordEncoder passwordEncoder){
        userExisted.setUsername(request.getUsername());
        userExisted.setEmail(request.getEmail());
        userExisted.setFullName(request.getFullName());
        userExisted.setPhoneNumber(request.getPhoneNumber());
        userExisted.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        return userExisted;
    }


}
