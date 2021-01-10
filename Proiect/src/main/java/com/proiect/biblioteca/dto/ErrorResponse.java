package com.proiect.biblioteca.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ErrorResponse {
    List<ErrorModel> errorMessage;
}
