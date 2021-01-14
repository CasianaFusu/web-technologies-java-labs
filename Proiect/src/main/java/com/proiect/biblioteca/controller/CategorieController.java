package com.proiect.biblioteca.controller;

import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.dto.CarteDto;
import com.proiect.biblioteca.dto.CategorieDto;
import com.proiect.biblioteca.mapper.CategorieMapper;
import com.proiect.biblioteca.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categorii")
public class CategorieController {
    private final CategorieService service;
    private final CategorieMapper mapper;

    @Autowired
    public CategorieController(CategorieService service, CategorieMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping()
    public ResponseEntity<List<CategorieDto>> getAll(){
        List<Categorie> categorii = service.getAll();
        return new ResponseEntity<List<CategorieDto>>(mapper.toDto(categorii), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategorieDto> findById(@PathVariable int id){
        Categorie categorie = service.findById(id);
        return new ResponseEntity<CategorieDto>(mapper.toDto(categorie),HttpStatus.OK);
    }

    @PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategorieDto> create(@Valid @RequestBody CategorieDto request){
        Categorie categorie = service.create(mapper.toEntity(request));
        return new ResponseEntity<CategorieDto>(mapper.toDto(categorie),HttpStatus.OK);
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> update(@Valid @RequestBody CategorieDto request){
        int affectedRows = service.update(mapper.toEntity(request));
        return new ResponseEntity<Integer>(affectedRows,HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        String result = service.delete(id);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}
