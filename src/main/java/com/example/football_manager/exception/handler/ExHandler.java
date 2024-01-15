package com.example.football_manager.exception.handler;

import com.example.football_manager.exception.EntityNotExistsException;
import com.example.football_manager.exception.LackOfFundsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExHandler {
    @ExceptionHandler(EntityNotExistsException.class)
    protected ResponseEntity<Object> handleEntityNotExistsException(EntityNotExistsException ex) {
        return buildResponseEntity(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(LackOfFundsException.class)
    protected ResponseEntity<?> handleEntityNotExistsException(LackOfFundsException ex) {
        return buildResponseEntity(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    private ResponseEntity<Object> buildResponseEntity(HttpStatus status, String exception) {
        ErrorMessage apiError = new ErrorMessage(status);
        apiError.setMessage(exception);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
