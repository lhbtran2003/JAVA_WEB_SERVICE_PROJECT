package com.example.internship.config.security.exception;

import com.example.internship.dto.response.ApiResponse;
import com.example.internship.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;

import java.io.IOException;
import java.time.LocalDateTime;


public class AccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403 Forbidden
        response.setContentType("application/json");
        response.getWriter().write(String.valueOf(ApiResponse.<String>builder()
                .success(false)
                .message(accessDeniedException.getMessage())
                .timestamp(LocalDateTime.now())
                .build()));
        response.getWriter().flush();
        response.getWriter().close();
    }
}