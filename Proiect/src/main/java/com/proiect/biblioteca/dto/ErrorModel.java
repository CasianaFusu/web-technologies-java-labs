package com.proiect.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel{
    private String fieldName;
    private int status;
    private Object rejectedValue;
    private String messageError;
}