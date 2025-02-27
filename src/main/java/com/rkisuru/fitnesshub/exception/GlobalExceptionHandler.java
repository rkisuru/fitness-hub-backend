package com.rkisuru.fitnesshub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<?> handleOperationNotPermittedException(OperationNotPermittedException ex) {

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), "OPERATION_NOT_PERMITTED");
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException ex) {

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), "NOT_FOUND");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {

        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), "INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
