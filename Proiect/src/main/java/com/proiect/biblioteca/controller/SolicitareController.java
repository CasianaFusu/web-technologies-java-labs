package com.proiect.biblioteca.controller;

import com.proiect.biblioteca.domain.Solicitare;
import com.proiect.biblioteca.dto.SolicitareDto;
import com.proiect.biblioteca.mapper.SolicitareMapper;
import com.proiect.biblioteca.service.SolicitareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("solicitari")
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
}