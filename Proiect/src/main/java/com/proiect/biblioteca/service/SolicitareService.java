package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.domain.Solicitare;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.ImprumutRepository;
import com.proiect.biblioteca.repository.SolicitareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitareService {
    private final SolicitareRepository solicitareRepository;
    private final ImprumutRepository imprumutRepository;

    public SolicitareService(SolicitareRepository solicitareRepository, ImprumutRepository imprumutRepository){
        this.solicitareRepository = solicitareRepository;
        this.imprumutRepository = imprumutRepository;
    }

    public List<Solicitare> getAll(){
        return solicitareRepository.findAll();
    }
}
