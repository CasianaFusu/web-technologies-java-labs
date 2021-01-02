package com.proiect.biblioteca.domain;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Solicitare {
    private int id;
    private int idImprumut;
    private Date dataAmanare;
}
