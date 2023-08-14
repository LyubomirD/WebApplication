package com.example.web.BackendTests.CustomAnnotations;

import com.example.web.BackEnd.CustomAnnotations.PasswordFormatValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

@SpringBootTest(classes = PasswordFormatValidatorTest.class)
public class PasswordFormatValidatorTest {

    private PasswordFormatValidator validator;

    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp() {
        validator = new PasswordFormatValidator();
        context = mock(ConstraintValidatorContext.class);
    }

    @Test
    public void testValidPassword() {
        assertTrue(validator.isValid("ValidPa$$123", context));
    }

    @Test
    public void testNoLengthInvalidPassword() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            validator.isValid("False$1", context);
        });

        assertTrue(exception.getMessage().contains("Password needs to have 8 characters, 1 uppercase letter, 1 lowercase letter, 1 number, 1 special symbol"));
    }

    @Test
    public void testNoUppercaseInvalidPassword() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            validator.isValid("invalid!123", context);
        });

        assertTrue(exception.getMessage().contains("Password needs to have 8 characters, 1 uppercase letter, 1 lowercase letter, 1 number, 1 special symbol"));
    }

    @Test
    public void testNoSpecialSymbolInvalidPassword() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            validator.isValid("Invalid4123", context);
        });

        assertTrue(exception.getMessage().contains("Password needs to have 8 characters, 1 uppercase letter, 1 lowercase letter, 1 number, 1 special symbol"));
    }

    @Test
    public void testNoNumberInvalidPassword() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            validator.isValid("Invalid!$", context);
        });

        assertTrue(exception.getMessage().contains("Password needs to have 8 characters, 1 uppercase letter, 1 lowercase letter, 1 number, 1 special symbol"));
    }

    @Test
    public void testNoLowercaseInvalidPassword() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            validator.isValid("123FF$78910", context);
        });

        assertTrue(exception.getMessage().contains("Password needs to have 8 characters, 1 uppercase letter, 1 lowercase letter, 1 number, 1 special symbol"));
    }

    @Test
    public void testNullPasswordInvalid() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            validator.isValid(null, context);
        });

        assertFalse(exception.getMessage().contains("Password needs to have 8 characters, 1 uppercase letter, 1 lowercase letter, 1 number, 1 special symbol"));
    }

    @Test
    public void testEmptyPasswordInvalid() {
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            validator.isValid("", context);
        });

        assertFalse(exception.getMessage().contains("Password needs to have 8 characters, 1 uppercase letter, 1 lowercase letter, 1 number, 1 special symbol"));
    }



}

