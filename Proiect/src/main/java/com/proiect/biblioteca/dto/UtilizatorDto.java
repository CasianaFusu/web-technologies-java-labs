package com.proiect.biblioteca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< Updated upstream
=======
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

>>>>>>> Stashed changes
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UtilizatorDto {
    private int id;
<<<<<<< Updated upstream
    private String username;
    private String nume;
    private String prenume;
    private String email;
    private String parola;
=======

    @NotBlank(message = "Completati campul parola")
    @NotNull(message = "Completati campul username")
    private String username;

    @NotBlank(message = "Completati campul parola")
    @NotNull(message = "Completati campul nume")
    private String nume;

    @NotBlank(message = "Completati campul parola")
    @NotNull(message = "Completati campul prenume")
    private String prenume;

    @NotNull(message = "Completati campul email")
    @Email(message = "Introduceti o adresa de email valida")
    private String email;

    @NotBlank(message = "Completati campul parola")
    @NotNull(message = "Completati campul parola")
    private String parola;

>>>>>>> Stashed changes
    private boolean activat;
    private int idRol;
}

<<<<<<< Updated upstream
//todo: loginDTO
=======
//todo: idROL by default ce valoare are? activat default e 0
>>>>>>> Stashed changes
