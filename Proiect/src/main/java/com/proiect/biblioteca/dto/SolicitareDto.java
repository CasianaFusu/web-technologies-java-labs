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
public class SolicitareDto {
    private int id;
    private int idImprumut;
    private Date termenAmanare;
    private Boolean aprobat;
}
