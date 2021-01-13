package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.domain.Solicitare;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.ImprumutRepository;
import com.proiect.biblioteca.repository.SolicitareRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitareService {
    private final SolicitareRepository solicitareRepository;
    private final ImprumutRepository imprumutRepository;
    private final AuthValidatorService authValidatorService;

    public SolicitareService(SolicitareRepository solicitareRepository,
                             ImprumutRepository imprumutRepository,
                             AuthValidatorService authValidatorService){
        this.solicitareRepository = solicitareRepository;
        this.imprumutRepository = imprumutRepository;
        this.authValidatorService = authValidatorService;
    }

    public Solicitare create(Solicitare request){
        var imprumut = imprumutRepository.findById(request.getIdImprumut());
        if(imprumut.isPresent())
            return solicitareRepository.create(request);
        else
            throw new BadRequestException("Nu exista un imprumut cu acest id");
    }

    public Solicitare aproba(int id,int idUtilizatorAutentificat){
        this.authValidatorService.ValidateBibliotecar(idUtilizatorAutentificat);
        var imprumut = imprumutRepository.findById(id);
        if(imprumut.isPresent())
            return solicitareRepository.aproba(id);
        else
            throw new BadRequestException("Nu exista un imprumut cu acest id");
    }

    public List<Solicitare> getAll(){
        return solicitareRepository.findAll();
    }
}
