package com.proiect.biblioteca.controller;

import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.dto.CarteDto;
import com.proiect.biblioteca.mapper.CarteMapper;
import com.proiect.biblioteca.service.CarteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarteDto> findById(@PathVariable int id){
        Carte carte = service.findById(id);
        return new ResponseEntity<CarteDto>(mapper.toDto(carte),HttpStatus.OK);
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarteDto> create(@Valid @RequestBody CarteDto request){
        Carte carte = service.create(mapper.toEntity(request));
        return new ResponseEntity<CarteDto>(mapper.toDto(carte),HttpStatus.CREATED);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> update(@RequestBody CarteDto request){
        int affectedRows = service.update(mapper.toEntity(request));
        return new ResponseEntity<Integer>(affectedRows,HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        String result = service.delete(id);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}
