package com.proiect.biblioteca.service;


import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.dto.CarteDto;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import com.proiect.biblioteca.repository.AutorRepository;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.ImprumutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteService {
    private final CarteRepository carteRepository;
    private final ImprumutRepository imprumutRepository;

    public CarteService(CarteRepository carteRepository, ImprumutRepository imprumutRepository){
        this.carteRepository = carteRepository;
        this.imprumutRepository = imprumutRepository;
    }

    public List<Carte> getAll(){
        return carteRepository.findAll();
    }

    public Carte findById(int id){
        return carteRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Carte"));
    }

    public Carte create(Carte request){
        return carteRepository.create(request);
    }

    public int update(Carte request) {
        return carteRepository.update(request);
    }

    public String delete(int id){
        List<Imprumut> imprumuturi = imprumutRepository.findByIdCarte(id);
        if(imprumuturi.size() == 0)
            return carteRepository.delete(id);
        else return "Exista carti imprumutate.";
    }
}
