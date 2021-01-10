package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    private final CategorieRepository categorieRepository;
    private final CarteRepository carteRepository;

    public CategorieService(CategorieRepository categorieRepository, CarteRepository carteRepository){
        this.categorieRepository = categorieRepository;
        this.carteRepository = carteRepository;
    }

    public List<Categorie> getAll(){
        return categorieRepository.findAll();
    }

    public Categorie findById(int id){
        return categorieRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Categorie"));
    }

    public Categorie create(Categorie request){
        return categorieRepository.create(request);
    }

    public int update(Categorie request){
        return categorieRepository.update(request);
    }

    public String delete(int id){
        List<Carte> carti = carteRepository.findByIdAutor(id);
        if(carti.size() == 0)
            return categorieRepository.delete(id);
        else return "Exista carti din aceasta categorie.";
    }
}
