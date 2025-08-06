package com.example.internship.utils.validation.add;


import com.example.internship.repository.UserRepository;
import com.example.internship.utils.validation.AbstractUniqueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UniqueEmailValidator extends AbstractUniqueValidator<UniqueEmail, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    protected boolean isValueExisted(String value) {
        Long count = userRepository.isExistEmail(value,null);
        return count > 0;
    }
}