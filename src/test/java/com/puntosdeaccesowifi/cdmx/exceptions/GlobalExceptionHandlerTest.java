package com.puntosdeaccesowifi.cdmx.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Test
    void handleException() {
        ResponseEntity<Object> response = globalExceptionHandler.handleException(new Exception("Error"));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

    }
}