package com.example.web.BackendTest.CustomExceptionTest;

import com.example.web.BackEnd.CustomException.EmailNotFoundException;
import com.example.web.BackEnd.CustomException.ValidationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = ValidationExceptionTest.class)
public class ValidationExceptionTest {

    @Test
    public void testValidationNotMeat() {
        assertThrows(ValidationException.class, () -> {
            throw new ValidationException("Invalid credentials");
        });
    }
}
