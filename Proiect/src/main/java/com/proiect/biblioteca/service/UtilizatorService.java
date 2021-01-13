package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.*;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.ImprumutRepository;
import com.proiect.biblioteca.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UtilizatorService {
    private final UtilizatorRepository utilizatorRepository;
    private final UtilizatorValidatorService validator;

    public UtilizatorService(UtilizatorRepository utilizatorRepository,
                             UtilizatorValidatorService validator){
        this.utilizatorRepository = utilizatorRepository;
        this.validator = validator;
    }

    public List<Utilizator> getAll(){

        return utilizatorRepository.findAll();
    }

    public Utilizator findById(int id){
        return utilizatorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Utilizator"));
    }

    public Utilizator create(Utilizator request){
        return utilizatorRepository.create(request);
    }

    public int update(Utilizator request) {
        return utilizatorRepository.update(request);
    }

    public int activate(int id){
        return utilizatorRepository.activate(id);
    }

    public Utilizator findByUsername(String username){
        return utilizatorRepository.findByUsername(username).get();
    }

    public String delete(int id){
        validator.validateRequestBeforeDelete(id);
        return utilizatorRepository.delete(id);
    }
}
