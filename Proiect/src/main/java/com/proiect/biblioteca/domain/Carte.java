package com.proiect.biblioteca.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Carte {
    private int id;
    private String isbn;
    private String nume;
    private String prenume;
    private int autorId;
    private Date dataAdaugare;
    private int categorieId;
    private int stoc;
}
