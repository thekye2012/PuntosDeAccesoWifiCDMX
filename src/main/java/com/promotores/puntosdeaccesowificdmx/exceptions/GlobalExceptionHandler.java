package com.promotores.puntosdeaccesowificdmx.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Manejador global de excepciones no contempladas específicamente.
     *
     * @param ex La excepción lanzada
     * @return Respuesta HTTP con información sobre el error.
     */
    //@ExceptionHandler(Exception.class)
    //public ResponseEntity<Object> handleException(Exception ex) {
    //    Map<String, Object> errorResponse = new HashMap<>();
    //    errorResponse.put("error", true); // Se especifica que hubo un error
    //    errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value()); // Código de error 500
    //    errorResponse.put("message", "Error al procesar la solicitud"); // Mensaje genérico
//
    //    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    //}

}
