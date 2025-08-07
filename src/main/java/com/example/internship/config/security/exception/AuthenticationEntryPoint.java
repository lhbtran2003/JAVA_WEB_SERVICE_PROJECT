package com.example.internship.config.security.exception;

import com.example.internship.dto.response.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;
import java.time.LocalDateTime;

public class AuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 403 Forbidden
        response.setContentType("application/json");
        response.getWriter().write(String.valueOf(ApiResponse.<String>builder()
                .success(false)
                .message(authException.getMessage())
                .timestamp(LocalDateTime.now())
                .build()));
        response.getWriter().flush();
        response.getWriter().close();
    }
}
