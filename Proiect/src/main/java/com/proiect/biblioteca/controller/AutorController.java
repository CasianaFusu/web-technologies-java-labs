package com.proiect.biblioteca.controller;

import com.proiect.biblioteca.domain.Autor;
import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.dto.AutorDto;
import com.proiect.biblioteca.dto.CarteDto;
import com.proiect.biblioteca.mapper.AutorMapper;
import com.proiect.biblioteca.mapper.CarteMapper;
import com.proiect.biblioteca.service.AutorService;
import com.proiect.biblioteca.service.CarteService;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autori")
public class AutorController {
    private final AutorMapper mapper;
    private final AutorService service;

    @Autowired
    public AutorController(AutorMapper autorMapper, AutorService autorService) {
        this.mapper = autorMapper;
        this.service = autorService;
    }

    @GetMapping()
    public ResponseEntity<List<AutorDto>> getAll(){
        List<Autor> autori = service.getAll();
        return new ResponseEntity<List<AutorDto>>(mapper.toDto(autori),HttpStatus.OK);
    }

    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutorDto> getById(@PathVariable int id){
        Autor autor = service.findById(id);
        return new ResponseEntity<AutorDto>(mapper.toDto(autor), HttpStatus.OK);
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AutorDto> create(@RequestBody AutorDto request){
        Autor autor = service.create(mapper.toEntity(request));
        return new ResponseEntity<AutorDto>(mapper.toDto(autor),HttpStatus.CREATED);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> update(@RequestBody AutorDto request){
        int affectedRows = service.update(mapper.toEntity(request));
        return new ResponseEntity<Integer>(affectedRows,HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        String result = service.delete(id);
        return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
    }
}
