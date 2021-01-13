package com.proiect.biblioteca.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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

    @NotNull
    @Size(min = 13, max = 13, message = ISBN_INVALID_LENGTH)
    private String isbn;

    @NotNull(message = NOT_NULL)
    private String nume;

    @NotNull
    private int idAutor;

    @NotNull
    private int idCategorie;

    @NotNull
    private Date dataAdaugare;

    @NotNull
    private int stoc;
}
