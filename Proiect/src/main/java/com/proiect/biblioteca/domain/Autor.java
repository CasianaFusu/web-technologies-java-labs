package com.proiect.biblioteca.domain;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Autor {
    private int id;
    @NotNull
    private String nume;
    private String prenume;
}
