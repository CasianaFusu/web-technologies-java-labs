package com.proiect.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImprumutDto {
    private int id;
    private int idUtilizator;
    private int idCarte;
    private Date dataImprumut;
    private Date dataExpirare;
    private boolean incheiat;
}
