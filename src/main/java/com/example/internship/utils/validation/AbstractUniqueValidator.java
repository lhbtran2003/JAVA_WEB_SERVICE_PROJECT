package com.example.internship.utils.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public abstract class AbstractUniqueValidator<T extends Annotation, V> implements ConstraintValidator<T, V> {

    // isValueExisted: quyết định value có tồn tại hay ko
    protected abstract boolean isValueExisted(V value);

    // Thêm method mới cho trường hợp có studentId
    protected boolean isValueExisted(String value, Integer studentId) {
        return isValueExisted((V) value);
    }

    @Override
    public boolean isValid(V s, ConstraintValidatorContext constraintValidatorContext) {
        return s == null || !isValueExisted(s);
    }

    // Method cho validation phức tạp (Object level)
    // Có gọi tới isValueExisted
    protected boolean isValidForObject(Object obj, ConstraintValidatorContext context, String fieldName) {
        if (obj == null) {
            return true;
        }

        try {
            // Lấy value và id từ object
            Field emailField = obj.getClass().getDeclaredField(fieldName);
            Field idField = obj.getClass().getDeclaredField("id");

            // cho phép reflection kể cả field đó là private
            emailField.setAccessible(true);
            idField.setAccessible(true);

            String value = (String) emailField.get(obj);
            Integer studentId = (Integer) idField.get(obj);

            if (value == null || value.trim().isEmpty()) {
                return true;
            }

            boolean exists = isValueExisted(value, studentId);

            if (exists) {
                // Gắn lỗi vào field value
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                        .addPropertyNode(fieldName)//gán lỗi trưc tiếp vào field đó
                        .addConstraintViolation();
                return false;
            }

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
