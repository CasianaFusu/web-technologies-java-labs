package com.proiect.biblioteca.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Imprumut {
    private int id;
    private int utilizatorId;
    private int carteId;
    private Date dataImprumut;
    private Date dataExpirare;
    private boolean incheiat;
}
