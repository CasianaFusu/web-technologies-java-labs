package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.domain.Solicitare;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import com.proiect.biblioteca.exception.PropertyNotGoodException;
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
        if(imprumut.isPresent()){
            if(imprumut.get().isIncheiat() == true){
                throw new PropertyNotGoodException("Incheiat", "Nu puteti solicita amanarea pentru un imprumut incheiat.");
            }
            return solicitareRepository.create(request);
        }

        else
            throw new EntityNotFoundException("Imprumut");
    }

    public Solicitare aproba(int id,int idUtilizatorAutentificat){
        this.authValidatorService.ValidateBibliotecar(idUtilizatorAutentificat);

        Solicitare solicitare = solicitareRepository.findById(id).get();
        if(solicitare.getAprobat()== true){
            throw new PropertyNotGoodException("Aprobat", "Aceasta solicitare a fost deja aprobata");
        }

        var imprumut = imprumutRepository.findById(solicitareRepository.findById(id).get().getIdImprumut());
        if(imprumut.isPresent()){
            imprumutRepository.changeTermenExpirareImprumut(solicitare.getIdImprumut(),solicitare.getTermenAmanare());
            return solicitareRepository.aproba(id);
        }
        else
            throw new EntityNotFoundException("Imprumut");
    }

    public List<Solicitare> getAll(){
        return solicitareRepository.findAll();
    }
}
