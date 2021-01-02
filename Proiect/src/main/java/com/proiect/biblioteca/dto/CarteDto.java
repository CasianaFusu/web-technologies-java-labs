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
public class CarteDto {
    private int id;
    private String isbn;
    private String nume;
    private Date dataAdaugare;
    private int stoc;
}
