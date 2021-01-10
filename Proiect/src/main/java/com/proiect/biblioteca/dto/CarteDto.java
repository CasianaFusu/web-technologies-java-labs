package com.proiect.biblioteca.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarteDto {
    private static final String ISBN_INVALID_LENGTH = "ISBN trebuie sa contina 13 caractere";
    private static final String NOT_NULL = "Completati numele cartii";

    private int id;
    @Size(min = 13, max = 13, message = ISBN_INVALID_LENGTH)
    private String isbn;
    @NotNull(message = NOT_NULL)
    private String nume;
    private int idAutor;
    private int idCategorie;
    private Date dataAdaugare;
    private int stoc;
}
