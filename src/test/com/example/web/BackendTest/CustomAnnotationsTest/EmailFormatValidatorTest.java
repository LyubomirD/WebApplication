package com.example.web.BackendTest.CustomAnnotationsTest;

import com.example.web.BackEnd.CustomAnnotations.EmailFormatValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = EmailFormatValidatorTest.class)
public class EmailFormatValidatorTest {

    private EmailFormatValidator emailFormatValidator;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp() {
        emailFormatValidator = new EmailFormatValidator();
    }

    @Test
    public void validEmail() {
        assertTrue(emailFormatValidator.isValid("example@example.com", context), "Valid Email");
    }

    @Test
    public void invalidEmailId() {
        assertThrows(ValidationException.class, () -> {
            assertFalse(emailFormatValidator.isValid("example", context), "Invalid email");
        });
    }

    @Test
    public void invalidEmailMissingSymbol() {
        assertThrows(ValidationException.class, () -> {
            assertFalse(emailFormatValidator.isValid("example.example.com", context), "Missing @ symbol");
        });
    }

    @Test
    public void invalidEmailMissingDomain() {
        assertThrows(ValidationException.class, () -> {
            assertFalse(emailFormatValidator.isValid("example@example", context), "Missing domain name");
        });
    }

    @Test
    public void invalidEmailNoDomain() {
        assertThrows(ValidationException.class, () -> {
            assertFalse(emailFormatValidator.isValid("example@examplecom.", context), "No domain name");
        });
    }

    @Test
    public void invalidNullEmail() {
        assertThrows(ValidationException.class, () -> {
            assertFalse(emailFormatValidator.isValid(null, context), "Null value for email");
        });
    }
}
