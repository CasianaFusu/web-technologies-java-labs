package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Autor;
import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.exception.PropertyNotGoodException;
import com.proiect.biblioteca.repository.AutorRepository;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieValidatorService {
    private final CategorieRepository categorieRepository;
    private final CarteRepository carteRepository;

    @Autowired
    public CategorieValidatorService(CarteRepository carteRepository, CategorieRepository categorieRepository){
        this.categorieRepository = categorieRepository;
        this.carteRepository = carteRepository;
    }

    public int validateRequest(Categorie request) {
        String pattern = "[A-Za-z0-9 ]*";

        if (!request.getNume().matches(pattern)) {
            throw new PropertyNotGoodException("Nume categorie", "contine caractere ilegale!");
        }

        var categorie = categorieRepository.findByName(request.getNume());
        if(categorie.isPresent())
        {
            if(categorie.get().getId() != request.getId())
              throw new PropertyNotGoodException("Categorie","mai exista o categorie cu acest nume!");
        }
        return 0;
    }

    public int validateDelete(int requestId)
    {
        List<Carte> carti = carteRepository.findByIdCategorie(requestId);
        var carte = carteRepository.findById(requestId);

        if(carti.size()>0)
        {
            throw new BadRequestException("Exista carti din aceasta categorie: " + carte.get().getNume());
        }
        return 0;
    }
}
