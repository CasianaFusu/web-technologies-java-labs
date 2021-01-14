package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Autor;
import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.exception.PropertyNotGoodException;
import com.proiect.biblioteca.repository.AutorRepository;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.CategorieRepository;
import com.proiect.biblioteca.repository.ImprumutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CarteValidatorService {

    private final ImprumutRepository imprumutRepository;
    private final CarteRepository carteRepository;
    private final AutorRepository autorRepository;
    private final CategorieRepository categorieRepository;

    @Autowired
    public CarteValidatorService(ImprumutRepository imprumutRepository,
                                 CarteRepository carteRepository,
                                 AutorRepository autorRepository,
                                 CategorieRepository categorieRepository) {
        this.imprumutRepository = imprumutRepository;
        this.carteRepository = carteRepository;
        this.autorRepository = autorRepository;
        this.categorieRepository = categorieRepository;
    }

    public int validateRequest(Carte request) {
        if (request.getDataAdaugare().before( new Date())){
            throw new PropertyNotGoodException("DataAdaugare","este anterioara zilei de azi!");
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

        var autor = autorRepository.findById(request.getIdAutor());
        if(!autor.isPresent())
        {
            throw new PropertyNotGoodException("idAutor", "Nu exista acest autor in baza de date!");
        }

        var categorie = categorieRepository.findById(request.getIdCategorie());
        if(!categorie.isPresent())
        {
            throw new PropertyNotGoodException("idCategorie", "Nu exista acesta categorie in baza de date!");
        }


        return 0;
   }

   public int validateUpdateRequest(Carte request) {
       if (request.getStoc()<0){
           throw new PropertyNotGoodException("Stoc","este mai mic decat zero!");

       }
       String pattern = "[A-Za-z0-9 ]*";
       if (!request.getNume().matches(pattern)){
           throw new PropertyNotGoodException("Nume","contine caractere gresite!");

       }
       var carte  = carteRepository.findByName(request.getNume());
       if(carte.isPresent())
       {
           throw new PropertyNotGoodException("Nume","mai exista o carte cu numele asta!");
       }

       var autor = autorRepository.findById(request.getIdAutor());
       if(!autor.isPresent())
       {
           throw new PropertyNotGoodException("idAutor", "Nu exista acest autor in baza de date!");
       }

       var categorie = categorieRepository.findById(request.getIdCategorie());
       if(!categorie.isPresent())
       {
           throw new PropertyNotGoodException("idCategorie", "Nu exista acesta categorie in baza de date!");
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
