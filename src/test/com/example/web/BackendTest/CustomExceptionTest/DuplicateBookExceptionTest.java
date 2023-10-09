package com.example.web.BackendTest.CustomExceptionTest;

import com.example.web.BackEnd.CustomException.DuplicateBookException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = DuplicateBookExceptionTest.class)
public class DuplicateBookExceptionTest {

    @Test
    public void testDuplicateBookException() {
        assertThrows(DuplicateBookException.class, () -> {
            throw new DuplicateBookException("A book with the same title but a different author already exists.");
        });
    }

}
