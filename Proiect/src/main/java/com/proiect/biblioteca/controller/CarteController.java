package com.proiect.biblioteca.controller;

import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.dto.CarteDto;
import com.proiect.biblioteca.mapper.CarteMapper;
import com.proiect.biblioteca.service.CarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("carti")
public class CarteController {
    private final CarteMapper mapper;
    private final CarteService service;

    @Autowired
    public CarteController(CarteMapper carteMapper, CarteService carteService) {
        this.mapper = carteMapper;
        this.service = carteService;
    }

    @GetMapping()
    public ResponseEntity<List<CarteDto>> getAll(){
        List<Carte> carti = service.getAll();
        return new ResponseEntity<List<CarteDto>>(mapper.toDto(carti),HttpStatus.OK);
    }
}
