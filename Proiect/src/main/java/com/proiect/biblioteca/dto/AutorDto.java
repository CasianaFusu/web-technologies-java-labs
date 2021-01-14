package com.proiect.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AutorDto {

    private int id;
    @NotNull(message = "Completați câmpul Nume")
    private String nume;
    @NotNull(message = "Completați câmpul Prenume")
    private String prenume;
}
