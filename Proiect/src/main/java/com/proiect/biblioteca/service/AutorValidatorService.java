package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Autor;
import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.exception.PropertyNotGoodException;
import com.proiect.biblioteca.repository.AutorRepository;
import com.proiect.biblioteca.repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutorValidatorService {

    private final AutorRepository autorRepository;
    private final CarteRepository carteRepository;

    @Autowired
    public AutorValidatorService(CarteRepository carteRepository, AutorRepository autorRepository){
        this.autorRepository = autorRepository;
        this.carteRepository = carteRepository;
    }

    public int validateRequest(Autor request) {
        String pattern = "[A-Za-z]*";

        if (!request.getNume().matches(pattern)) {
            throw new PropertyNotGoodException("Nume", "contine caractere ilegale!");
        }

        if (!request.getPrenume().matches(pattern)) {
            throw new PropertyNotGoodException("Prenume", "contine caractere ilegale!");
        }

        var autor = autorRepository.findByName(request.getNume(), request.getPrenume());
        if(autor.isPresent())
        {
            throw new PropertyNotGoodException("Nume","mai exista un autor cu acest nume!");
        }
        return 0;
    }

    public int validateDelete(int requestId)
    {
        List<Carte> carti = carteRepository.findByIdAutor(requestId);

        if(carti.size()>0)
        {
            throw new BadRequestException("Exista carti cu acest autor!");
        }
        return 0;
    }
}
