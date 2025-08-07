package com.example.internship.utils.validation.update;

import com.example.internship.repository.UserRepository;
import com.example.internship.utils.validation.AbstractUniqueValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailForUpdateValidator extends AbstractUniqueValidator<UniqueEmailForUpdate, Object> {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected boolean isValueExisted(Object value) {
        return false;
    }

    @Override
    protected boolean isValueExisted(String email, Integer userId) {
       return false;
    }

    @Override
    public boolean isValid(Object s, ConstraintValidatorContext constraintValidatorContext) {
        return isValidForObject(s, constraintValidatorContext,"email");
    }
}
