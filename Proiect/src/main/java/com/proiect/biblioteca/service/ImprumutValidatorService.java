package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.domain.Utilizator;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import com.proiect.biblioteca.exception.NotAuthorizedException;
import com.proiect.biblioteca.exception.PropertyNotGoodException;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.ImprumutRepository;
import com.proiect.biblioteca.repository.UtilizatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ImprumutValidatorService {

    private final ImprumutRepository imprumutRepository;
    private final CarteRepository carteRepository;
    private final UtilizatorRepository utilizatorRepository;

    @Autowired
    public ImprumutValidatorService(ImprumutRepository imprumutRepository,
                                    CarteRepository carteRepository,
                                    UtilizatorService utilizatorService,
                                    UtilizatorRepository utilizatorRepository) {
        this.imprumutRepository = imprumutRepository;
        this.carteRepository = carteRepository;
        this.utilizatorRepository = utilizatorRepository;

    }

    public int validateRequest(Imprumut request) {
        Optional<Carte> carte = carteRepository.findById(request.getIdCarte());
        Optional<Utilizator> utilizator = utilizatorRepository.findById(request.getIdUtilizator());

        if(!utilizator.isPresent()){
            throw new EntityNotFoundException("Utilizator");
        }

        if(!carte.isPresent()){
            throw new EntityNotFoundException("Carte");
        }

        if (carte.get().getStoc() <= 0) {
            throw new PropertyNotGoodException("Stoc = 0", "Nu mai sunt carti de acest tip!");
        }

        if(request.getDataImprumut().after(request.getDataExpirare())){
            throw new PropertyNotGoodException("Data imprumut/expirare", "Data de expirare nu poate fi anterioara datei imprumutului!");

        }

        if (request.getDataImprumut().after(new Date())) {
            throw new PropertyNotGoodException("Data de imprumut", "Data de imprumut este o data din viitor!");
        }

        return 0;
    }

    public int validateRequestBeforeUpdate(Imprumut request) {
        Optional<Imprumut> imprumut = imprumutRepository.findById(request.getId());

        if(!imprumut.isPresent()){
            throw new EntityNotFoundException("Imprumut");
        }

        if (imprumut.get().isIncheiat() == true)
            throw new PropertyNotGoodException("Incheiat", "Imprumutul a fost deja incheiat.");
        else {
            var carteCheck = carteRepository.findById(request.getIdCarte());

            if(!carteCheck.isPresent())
            {
                throw new PropertyNotGoodException("idCarte", "Nu exista aceasta carte in baza de date");
            }

            var utilizator = utilizatorRepository.findById(request.getIdUtilizator());
            if(!utilizator.isPresent())
            {
                throw new PropertyNotGoodException("idUtilizator", "Nu exista aceast utilizator in baza de date!");
            }

            if (request.getDataExpirare().before(request.getDataImprumut())) {
                throw new PropertyNotGoodException("Data de expirare", "este mai mica decat data de imprumut!");
            }

            if (request.getDataImprumut().after(new Date())) {
                throw new PropertyNotGoodException("Data de imprumut", "Este o data din viitor.");
            }

            if(request.isIncheiat() == true){
                carteRepository.increaseStocById(imprumut.get().getIdCarte());
                return 0;
            }

            if (imprumut.get().getIdCarte() != request.getIdCarte()) {
                Optional<Carte> carte = carteRepository.findById(request.getIdCarte());
                if (carte.get().getStoc() > 0) {
                    int resultDecreaseStoc = carteRepository.decreaseStocById(request.getIdCarte());
                    int resultIncreaseStoc = carteRepository.increaseStocById(imprumut.get().getIdCarte());
                } else throw new PropertyNotGoodException("Stoc", "Nu mai sunt carti pe stoc cu acest id.");
            }
        }
        return 0;

    }


}
