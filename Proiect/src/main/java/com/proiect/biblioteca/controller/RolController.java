package com.proiect.biblioteca.controller;

import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.domain.Rol;
import com.proiect.biblioteca.dto.CategorieDto;
import com.proiect.biblioteca.dto.RolDto;
import com.proiect.biblioteca.mapper.CategorieMapper;
import com.proiect.biblioteca.mapper.RolMapper;
import com.proiect.biblioteca.service.CategorieService;
import com.proiect.biblioteca.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roluri")
public class RolController {
    private final RolService service;
    private final RolMapper mapper;

    @Autowired
    public RolController(RolService service, RolMapper mapper){
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(path = "/{idUtilizatorAutentificat}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RolDto>> getAll(@PathVariable int idUtilizatorAutentificat){
        List<Rol> roluri = service.getAll(idUtilizatorAutentificat);
        return new ResponseEntity<List<RolDto>>(mapper.toDto(roluri), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}/{idUtilizatorAutentificat}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RolDto> findById(@PathVariable int id,@PathVariable int idUtilizatorAutentificat){
        Rol rol = service.findById(id,idUtilizatorAutentificat);
        return new ResponseEntity<RolDto>(mapper.toDto(rol),HttpStatus.OK);
    }

    @PostMapping(path = "/create/{idUtilizatorAutentificat}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RolDto> create(@RequestBody RolDto request,@PathVariable int idUtilizatorAutentificat){
        Rol rol = service.create(mapper.toEntity(request),idUtilizatorAutentificat);
        return new ResponseEntity<RolDto>(mapper.toDto(rol),HttpStatus.OK);
    }

    @PutMapping(path = "/update/{idUtilizatorAutentificat}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> update(@RequestBody RolDto request,@PathVariable int idUtilizatorAutentificat){
        int affectedRows = service.update(mapper.toEntity(request),idUtilizatorAutentificat);
        return new ResponseEntity<Integer>(affectedRows,HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}/{idUtilizatorAutentificat}")
    public ResponseEntity<String> delete(@PathVariable int id,@PathVariable int idUtilizatorAutentificat){
        String result = service.delete(id,idUtilizatorAutentificat);
        return new ResponseEntity<String>(result, HttpStatus.OK);
    }
}
