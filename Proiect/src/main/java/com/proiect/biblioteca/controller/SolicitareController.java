package com.proiect.biblioteca.controller;

import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.domain.Solicitare;
import com.proiect.biblioteca.dto.ImprumutDto;
import com.proiect.biblioteca.dto.SolicitareDto;
import com.proiect.biblioteca.mapper.SolicitareMapper;
import com.proiect.biblioteca.service.SolicitareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/solicitari")
public class SolicitareController {
    private final SolicitareMapper mapper;
    private final SolicitareService service;

    @Autowired
    public SolicitareController(SolicitareMapper mapper, SolicitareService service){
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<SolicitareDto>> getAll(){
        List<Solicitare> solicitari = service.getAll();
        return new ResponseEntity<List<SolicitareDto>>(mapper.toDto(solicitari), HttpStatus.OK);
    }


    @PostMapping(path ="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SolicitareDto> create(@Valid @RequestBody SolicitareDto request){
        Solicitare solicitare = service.create(mapper.toEntity(request));
        return new ResponseEntity<SolicitareDto>(mapper.toDto(solicitare), HttpStatus.CREATED);
    }


    @PostMapping(path ="/aproba/{id}/{idUtilizatorAutentificat}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SolicitareDto> create(@PathVariable int id,@PathVariable int idUtilizatorAutentificat){
        Solicitare solicitare = service.aproba(id,idUtilizatorAutentificat);
        return new ResponseEntity<SolicitareDto>(mapper.toDto(solicitare), HttpStatus.CREATED);
    }
}
