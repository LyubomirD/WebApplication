package com.example.web.BackendTest.CustomAnnotationsTest;


import com.example.web.BackEnd.CustomAnnotations.EmailFormatValidator;
import com.example.web.BackEnd.CustomAnnotations.PasswordFormatValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = PasswordFormatValidatorTest.class)
public class PasswordFormatValidatorTest {

    private PasswordFormatValidator passwordFormatValidator;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp() {
        passwordFormatValidator = new PasswordFormatValidator();
    }

    @Test
    public void validPassword() {
        assertTrue(passwordFormatValidator.isValid("Example$12345", context), "Valid password");
    }

    @Test
    public void invalidPassword() {
        assertThrows(ValidationException.class, () -> {
           assertFalse(passwordFormatValidator.isValid("example", context), "Invalid password");
        });
    }

    @Test
    public void invalidPasswordLength() {
        assertThrows(ValidationException.class, () -> {
            assertFalse(passwordFormatValidator.isValid("Ex@mpl1", context), "Invalid password length of 7 and not 8");
        });
    }

    @Test
    public void invalidPasswordNoUppercase() {
        assertThrows(ValidationException.class, () -> {
            assertFalse(passwordFormatValidator.isValid("example$12345", context), "Invalid password no uppercase");
        });
    }

    @Test
    public void invalidPasswordNoLowercase() {
        assertThrows(ValidationException.class, () -> {
            assertFalse(passwordFormatValidator.isValid("EXAMPLE$12345", context), "Invalid password no lowercase");
        });
    }

    @Test
    public void invalidPasswordNoSpecialSymbol() {
        assertThrows(ValidationException.class, () -> {
            assertFalse(passwordFormatValidator.isValid("Example12345", context), "Invalid password");
        });
    }

    @Test
    public void invalidPasswordNoNumbers() {
        assertThrows(ValidationException.class, () -> {
            assertFalse(passwordFormatValidator.isValid("Example$Example", context), "Invalid password no number");
        });
    }

    @Test
    public void invalidNullPassword() {
        assertThrows(ValidationException.class, () -> {
            assertFalse(passwordFormatValidator.isValid(null, context), "Null password");
        });
    }
}
