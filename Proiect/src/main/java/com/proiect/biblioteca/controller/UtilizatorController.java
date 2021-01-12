package com.proiect.biblioteca.controller;

import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Utilizator;
import com.proiect.biblioteca.dto.CarteDto;
import com.proiect.biblioteca.dto.UtilizatorDto;
import com.proiect.biblioteca.mapper.CarteMapper;
import com.proiect.biblioteca.mapper.UtilizatorMapper;
import com.proiect.biblioteca.service.CarteService;
import com.proiect.biblioteca.service.UtilizatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/utilizatori")
public class UtilizatorController {
    private final UtilizatorMapper mapper;
    private final UtilizatorService service;

    @Autowired
    public UtilizatorController(UtilizatorMapper mapper, UtilizatorService service) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<List<UtilizatorDto>> getAll(){
        List<Utilizator> utilizatori = service.getAll();
        return new ResponseEntity<List<UtilizatorDto>>(mapper.toDto(utilizatori), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UtilizatorDto> findById(@PathVariable int id){
        Utilizator utilizator = service.findById(id);
        return new ResponseEntity<UtilizatorDto>(mapper.toDto(utilizator),HttpStatus.OK);
    }



    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UtilizatorDto> create(@Valid @RequestBody UtilizatorDto request){
        Utilizator utilizator = service.create(mapper.toEntity(request));
        return new ResponseEntity<UtilizatorDto>(mapper.toDto(utilizator),HttpStatus.OK);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> update(@Valid @RequestBody UtilizatorDto request){
        int affectedRows = service.update(mapper.toEntity(request));
        return new ResponseEntity<Integer>(affectedRows,HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        String result = service.delete(id);
        return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
    }

    @PutMapping(path = "/activate/{id}")
    public ResponseEntity<Integer> activate(@PathVariable int id){
        int affectedRows = service.activate(id);
        return new ResponseEntity<Integer>(affectedRows,HttpStatus.OK);
    }

}
