package com.proiect.biblioteca.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Utilizator {
    private int id;
    private String nume;
    private String prenume;
    private String email;
    private String parola;
    private boolean activat;
    private int rolId;
}
