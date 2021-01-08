package com.proiect.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilizatorDto {
    private int id;
    private String username;
    private String nume;
    private String prenume;
    private String email;
    private String parola;
    private boolean activat;
    private int idRol;
}

//todo: loginDTO