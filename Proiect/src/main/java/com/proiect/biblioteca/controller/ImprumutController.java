package com.proiect.biblioteca.controller;

import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.dto.CategorieDto;
import com.proiect.biblioteca.dto.ImprumutDto;
import com.proiect.biblioteca.mapper.ImprumutMapper;
import com.proiect.biblioteca.service.ImprumutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imprumuturi")
public class ImprumutController {
    private final ImprumutService service;
    private final ImprumutMapper mapper;

    @Autowired
    public ImprumutController(ImprumutService service, ImprumutMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ImprumutDto>> getAll(){
        List<Imprumut> imprumuturi = service.getAll();
        return new ResponseEntity<List<ImprumutDto>>(mapper.toDto(imprumuturi), HttpStatus.OK);
    }

   @PostMapping(path ="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ImprumutDto> create(@RequestBody ImprumutDto request){
        Imprumut imprumut = service.create(mapper.toEntity(request));
        return new ResponseEntity<ImprumutDto>(mapper.toDto(imprumut), HttpStatus.OK);
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<ImprumutDto> getById(@PathVariable int id){
        Imprumut imprumut = service.getById(id);
        return new ResponseEntity<ImprumutDto>(mapper.toDto(imprumut), HttpStatus.OK);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> update(@RequestBody ImprumutDto request){
        int affectedRows = service.update(mapper.toEntity(request));
        return new ResponseEntity<Integer>(affectedRows,HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        String result = service.delete(id);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}