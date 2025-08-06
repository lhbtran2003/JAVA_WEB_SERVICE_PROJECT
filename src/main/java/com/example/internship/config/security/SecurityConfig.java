package com.example.internship.config.security;

import com.example.internship.config.security.exception.AccessDeniedHandler;
import com.example.internship.config.security.exception.AuthenticationEntryPoint;
import com.example.internship.config.security.jwt.JwtAuthTokenFilter;
import com.example.internship.config.security.jwt.JwtProvider;
import com.example.internship.config.security.principle.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String USER_ENDPOINT = "/api/users/**";
    private static final String STUDENT_ENDPOINT = "/api/students/**";
    private static final String MENTOR_ENDPOINT = "/api/mentors/**";
    private static final String INTERNSHIP_PHASE_ENDPOINT = "/api/internship_phases/**";
    private static final String EVALUATION_CRITERIA_ENDPOINT = "/api/evaluation_criteria/**";
    private static final String ASSESSMENT_ROUND_ENDPOINT = "/api/assessment_rounds/**";
    private static final String ROUND_CRITERIA_ENDPOINT = "/api/round_criteria/**";
    private static final String INTERNSHIP_ASSIGNMENT_ENDPOINT = "/api/internship_assignments/**";
    private static final String ASSIGNMENT_RESULT_ENDPOINT = "/api/assessment_results/**";

    private final UserDetailService userDetailsService;
    public final JwtProvider jwtProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthTokenFilter jwtAuthTokenFilter() {
        return new JwtAuthTokenFilter(jwtProvider, userDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        // auth
                        .requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/auth/me").authenticated()

                        //user
                        .requestMatchers(HttpMethod.GET, USER_ENDPOINT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, USER_ENDPOINT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, USER_ENDPOINT).hasRole("ADMIN")

                        //student
                        .requestMatchers(HttpMethod.GET, STUDENT_ENDPOINT).hasAnyRole("ADMIN", "MENTOR")
                        .requestMatchers(HttpMethod.GET, STUDENT_ENDPOINT).authenticated()
                        .requestMatchers(HttpMethod.POST, STUDENT_ENDPOINT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, STUDENT_ENDPOINT).hasAnyRole("ADMIN", "STUDENT")

                        //mentor
                        .requestMatchers(HttpMethod.GET, MENTOR_ENDPOINT).hasAnyRole("ADMIN", "STUDENT")
                        .requestMatchers(HttpMethod.GET, MENTOR_ENDPOINT).authenticated()
                        .requestMatchers(HttpMethod.POST, MENTOR_ENDPOINT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, MENTOR_ENDPOINT).hasAnyRole("ADMIN", "MENTOR")

                        //internships
                        .requestMatchers(HttpMethod.GET, INTERNSHIP_PHASE_ENDPOINT).authenticated()
                        .requestMatchers(HttpMethod.POST, INTERNSHIP_PHASE_ENDPOINT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, INTERNSHIP_PHASE_ENDPOINT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, INTERNSHIP_PHASE_ENDPOINT).hasRole("ADMIN")

                        //evaluation criteria
                        .requestMatchers(HttpMethod.GET, EVALUATION_CRITERIA_ENDPOINT).authenticated()
                        .requestMatchers(HttpMethod.POST, EVALUATION_CRITERIA_ENDPOINT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, EVALUATION_CRITERIA_ENDPOINT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, EVALUATION_CRITERIA_ENDPOINT).hasRole("ADMIN")

                        //assessment round
                        .requestMatchers(HttpMethod.GET, ASSESSMENT_ROUND_ENDPOINT).authenticated()
                        .requestMatchers(HttpMethod.POST, ASSESSMENT_ROUND_ENDPOINT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, ASSESSMENT_ROUND_ENDPOINT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, ASSESSMENT_ROUND_ENDPOINT).hasRole("ADMIN")

                        //round criteria
                        .requestMatchers(HttpMethod.GET, ROUND_CRITERIA_ENDPOINT).authenticated()
                        .requestMatchers(HttpMethod.POST, ROUND_CRITERIA_ENDPOINT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, ROUND_CRITERIA_ENDPOINT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, ROUND_CRITERIA_ENDPOINT).hasRole("ADMIN")

                        //internship assignment
                        .requestMatchers(HttpMethod.GET, INTERNSHIP_ASSIGNMENT_ENDPOINT).authenticated()
                        .requestMatchers(HttpMethod.POST, INTERNSHIP_ASSIGNMENT_ENDPOINT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, INTERNSHIP_ASSIGNMENT_ENDPOINT).hasRole("ADMIN")

                        //assignment result
                        .requestMatchers(HttpMethod.GET, EVALUATION_CRITERIA_ENDPOINT).authenticated()
                        .requestMatchers(HttpMethod.POST, EVALUATION_CRITERIA_ENDPOINT).hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, EVALUATION_CRITERIA_ENDPOINT).hasRole("ADMIN")
                        .anyRequest().authenticated()

                )
                .sessionManagement(
                        session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider())
                .exceptionHandling(ex ->
                        ex.authenticationEntryPoint(new AuthenticationEntryPoint())
                                .accessDeniedHandler(new AccessDeniedHandler())
                );
        return http.build();
    }
}
