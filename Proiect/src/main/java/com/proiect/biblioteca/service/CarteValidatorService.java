package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Carte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarteValidatorService {
    private final CarteService carteService;

    @Autowired
    public CarteValidatorService(CarteService carteService) {
        this.carteService = carteService;
    }

    /*public void validateRequest(Carte request) {
        if (request.getIdAutor()){

        }
    }*/
}
