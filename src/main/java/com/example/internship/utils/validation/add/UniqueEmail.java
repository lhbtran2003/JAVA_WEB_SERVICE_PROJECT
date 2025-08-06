package com.example.internship.utils.validation.add;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.ElementType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueEmail {
    String message() default "Email đã tồn tại!";
    Class<?>[] groups() default {};
    // Dùng cho validation nhóm (ít khi dùng ở mức cơ bản)

    Class<? extends Payload>[] payload() default {};
    // Dùng để thêm metadata nếu cần (ít khi dùng)
}