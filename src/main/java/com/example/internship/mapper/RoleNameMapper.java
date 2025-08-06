package com.example.internship.mapper;

import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.RoleName;
import com.example.internship.entity.User;

import java.util.List;

public class RoleNameMapper {
    public static RoleName mapRoleToRoleName (String role) {
        RoleName roleName = null;
        switch (role) {
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
        return roleName;
    }
}
