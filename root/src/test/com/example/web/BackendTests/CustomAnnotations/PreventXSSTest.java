package com.example.web.BackendTests.CustomAnnotations;

import com.example.web.BackEnd.CustomAnnotations.PreventXSS;
import com.example.web.BackEnd.RestApi.UserModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.validation.ConstraintValidatorContext;
import static org.mockito.Mockito.mock;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

@SpringBootTest(classes = PreventXSSTest.class)
public class PreventXSSTest {
    private PreventXSS validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    public void setUp() {
        validator = new PreventXSS();
        context = mock(ConstraintValidatorContext.class);
    }

    @Test
    public void testValidUserModel() {
        UserModel userModel = new UserModel("valid-username", "valid-email@example.com", "Valid-Password###123");
        assertTrue(validator.isValid(userModel, context));
    }

    @Test
    public void testWithXSSInvalidUsername() {
        UserModel userModel = new UserModel("<script>alert('XSS');</script>", "valid-email@example.com", "valid-password");
        assertFalse(validator.isValid(userModel, context));
    }

    @Test
    public void testWithXSSInvalidEmail() {
        UserModel userModel = new UserModel("valid-username", "<script>alert('XSS');</script>", "valid-password");
        assertFalse(validator.isValid(userModel, context));
    }

    @Test
    public void testWithXSSInvalidPassword() {
        UserModel userModel = new UserModel("valid-username", "valid-email@example.com", "<script>alert('XSS');</script>");
        assertFalse(validator.isValid(userModel, context));
    }

    @Test
    public void testWithAmpersandInvalidUsername() {
        UserModel userModel = new UserModel("valid-username&", "valid-email@example.com", "password");
        assertFalse(validator.isValid(userModel, context));
    }


    @Test
    public void testWithAmpersandInvalidEmail() {
        UserModel userModel = new UserModel("valid-username", "valid-email@example.com&", "password");
        assertFalse(validator.isValid(userModel, context));
    }


    @Test
    public void testWithAmpersandInvalidPassword() {
        UserModel userModel = new UserModel("valid-username", "valid-email@example.com", "password&");
        assertFalse(validator.isValid(userModel, context));
    }

}

