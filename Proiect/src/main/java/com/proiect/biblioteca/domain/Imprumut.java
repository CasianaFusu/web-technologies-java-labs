package com.proiect.biblioteca.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Imprumut {
    private int id;
    private int idUtilizator;
    private int idCarte;
    private Date dataImprumut;
    private Date dataExpirare;
    private boolean incheiat;
}
