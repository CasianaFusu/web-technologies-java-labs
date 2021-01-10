package com.proiect.biblioteca.domain;

import lombok.*;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Carte {
    private int id;
    private String isbn;
    private String nume;
    private int idAutor;
    private Date dataAdaugare;
    private int idCategorie;
    private int stoc;
}
