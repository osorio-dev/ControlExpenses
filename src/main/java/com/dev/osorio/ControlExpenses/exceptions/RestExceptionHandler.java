package com.dev.osorio.ControlExpenses.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<String> expenseNotFound(NotFoundException notFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(notFoundException.getMessage());
    }

    @ExceptionHandler(InvalidRequestException.class)
    private ResponseEntity<String> invalidRequest(InvalidRequestException invalidRequestException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(invalidRequestException.getMessage());
    }
}
