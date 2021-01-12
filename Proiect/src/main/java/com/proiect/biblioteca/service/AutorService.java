package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Autor;
import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.dto.CarteDto;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import com.proiect.biblioteca.repository.AutorRepository;
import com.proiect.biblioteca.repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorService {
    private final AutorRepository autorRepository;
    private final CarteRepository carteRepository;
    private final AutorValidatorService validatorService;

    public AutorService(AutorRepository autorRepository, CarteRepository carteRepository, AutorValidatorService autorValidatorService){
        this.autorRepository = autorRepository;
        this.carteRepository = carteRepository;
        this.validatorService = autorValidatorService;
    }

    public List<Autor> getAll(){
        return autorRepository.findAll();
    }

    public Autor findById(int id){
        return autorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Autor"));
    }

    public Autor create(Autor autor){
        validatorService.validateRequest(autor);
        return autorRepository.create(autor);
    }

    public int update(Autor request) {
        validatorService.validateRequest(request);
        return autorRepository.update(request);
    }

    public String delete(int id){
        validatorService.validateDelete((id));
        return autorRepository.delete(id);
    }
}
