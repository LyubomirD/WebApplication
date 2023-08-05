package com.example.web.BackEnd.CustomException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

public class ValidationException extends ConstraintViolationException {

    public ValidationException(String message) {
        super(message, null);
    }
}
