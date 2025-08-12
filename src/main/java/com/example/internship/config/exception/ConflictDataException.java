package com.example.internship.config.exception;

import com.example.internship.dto.response.FieldErrorResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConflictDataException extends RuntimeException{
   private final List<FieldErrorResponse> errors;
    public ConflictDataException(List<FieldErrorResponse> errors) {
        super("ConflictDataException");
        this.errors = errors;
    }
}
