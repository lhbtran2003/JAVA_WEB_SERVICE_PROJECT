package com.example.internship;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class JavaWebServiceProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaWebServiceProjectApplication.class, args);

    }

//    @Bean
//    public CommandLineRunner commandLineRunner(PasswordEncoder passwordEncoder) {
//
//        return args -> {
//            String rawPassword1 = "admin123";
//            String encodedPassword1 = passwordEncoder.encode(rawPassword1);
//            System.out.println("👉 Raw password admin: " + rawPassword1);
//            System.out.println("🔐 Hashed password admin: " + encodedPassword1);
//
//            String rawPassword2 = "mentor123";
//            String encodedPassword2 = passwordEncoder.encode(rawPassword2);
//            System.out.println("👉 Raw password mentor: " + rawPassword2);
//            System.out.println("🔐 Hashed password mentor: " + encodedPassword2);
//
//            String rawPassword3 = "student123";
//            String encodedPassword3 = passwordEncoder.encode(rawPassword3);
//            System.out.println("👉 Raw password student: " + rawPassword3);
//            System.out.println("🔐 Hashed password student: " + encodedPassword3);
//        };
//    }


}
