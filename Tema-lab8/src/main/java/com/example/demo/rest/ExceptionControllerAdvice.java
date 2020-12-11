package com.example.demo.rest;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.OwnerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleEntityNotFound(OwnerNotFoundException ex) {
        log.debug("Entity not found" + ex);
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(404)
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleEntityNotFound(BadRequestException ex) {
        log.debug("Bad request" + ex);
        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(400)
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.debug("Invalid request" + ex);

        String invalidFields = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .code(400)
                        .message("Urmatoarele campuri nu sunt valide: " + invalidFields)
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

}
