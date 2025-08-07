package com.example.internship.utils.validation.update;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 2. Annotation cho Update (kiểm tra email không trùng trừ của chính mình)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailForUpdateValidator.class)
public @interface UniqueEmailForUpdate {
    String message() default "Email đã được sử dụng bởi người khác";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
