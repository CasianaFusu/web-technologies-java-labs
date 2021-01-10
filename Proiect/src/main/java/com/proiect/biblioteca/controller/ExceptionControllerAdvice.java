package com.proiect.biblioteca.controller;

import com.proiect.biblioteca.dto.ErrorModel;
import com.proiect.biblioteca.dto.ErrorResponse;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException ex) {
        ErrorModel errorModel = new ErrorModel("Message",404, "", ex.getMessage());
        List<ErrorModel> errorModelList = new ArrayList<ErrorModel>();
        errorModelList.add(errorModel);

        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .errorMessage(errorModelList)
                        .build()
                , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleBadRequest(BadRequestException ex) {
        ErrorModel errorModel = new ErrorModel("Message", 404, "", ex.getMessage());
        List<ErrorModel> errorModelList = new ArrayList<ErrorModel>();
        errorModelList.add(errorModel);

        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .errorMessage(errorModelList)
                        .build()
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorModel> errorMessages = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorModel(err.getField(), 400, err.getRejectedValue(), err.getDefaultMessage()))
                .distinct()
                .collect(Collectors.toList());

        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .errorMessage(errorMessages)
                        .build()
                , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    protected  ResponseEntity<ErrorResponse> handlerMethodNotAllowed(HttpClientErrorException.MethodNotAllowed ex){
        ErrorModel errorModel = new ErrorModel("Message", 405, "", ex.getMessage());
        List<ErrorModel> errorModelList = new ArrayList<ErrorModel>();
        errorModelList.add(errorModel);

        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .errorMessage(errorModelList)
                        .build()
                , HttpStatus.METHOD_NOT_ALLOWED);
    }
}


