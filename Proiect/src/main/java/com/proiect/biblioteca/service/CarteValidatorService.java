package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.exception.PropertyNotGoodException;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.ImprumutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CarteValidatorService {

    private final ImprumutRepository imprumutRepository;
    private final CarteRepository carteRepository;

    @Autowired
    public CarteValidatorService( ImprumutRepository imprumutRepository,
                                  CarteRepository carteRepository) {
        this.imprumutRepository = imprumutRepository;
        this.carteRepository = carteRepository;
    }

    public int validateRequest(Carte request) {
        if (request.getDataAdaugare().before( new Date())){
            throw new PropertyNotGoodException("DataAdaugare","e anterioara zilei de azi!");
        }
        if (request.getStoc()<0){
            throw new PropertyNotGoodException("Stoc","este mai mic decat zero!");

        }
        String pattern = "[A-Za-z0-9 ]*";
        if (!request.getNume().matches(pattern)){
            throw new PropertyNotGoodException("Nume","contine caractere ilegale!");

        }
        var carte  = carteRepository.findByName(request.getNume());
        if(carte.isPresent())
        {
            throw new PropertyNotGoodException("Nume","mai exista o carte cu numele asta!");
        }
        return 0;
   }

   public int validateDelete(int requestId)
   {
       List<Imprumut> imprumuturi = imprumutRepository.findByIdCarte(requestId);
       var carte = carteRepository.findById(requestId);

       if(imprumuturi.size()>0)
       {
           throw new BadRequestException("Exista imprumuturi pentru cartea " + carte.get().getNume());
       }
       return 0;
   }
}
