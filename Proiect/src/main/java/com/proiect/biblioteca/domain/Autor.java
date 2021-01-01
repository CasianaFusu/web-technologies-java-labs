package com.proiect.biblioteca.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Autor {
    private int id;
    private String nume;
    private String prenume;
}
