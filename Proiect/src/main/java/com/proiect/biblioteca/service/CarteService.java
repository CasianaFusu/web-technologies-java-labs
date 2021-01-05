package com.proiect.biblioteca.service;


import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.dto.CarteDto;
import com.proiect.biblioteca.repository.AutorRepository;
import com.proiect.biblioteca.repository.CarteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarteService {
    private final CarteRepository carteRepository;

    public CarteService(CarteRepository carteRepository){
        this.carteRepository = carteRepository;
    }

    public List<Carte> getAll(){
        return carteRepository.findAll();
    }

    public Carte findById(int id){
        return carteRepository.findById(id).orElseThrow(()-> new RuntimeException("Cartea nu a fost gasita."));
    }

    public Carte create(Carte request){
        return carteRepository.create(request);
    }

    public int update(Carte request) {
        return carteRepository.update(request);
    }

    public String delete(int id){
        return carteRepository.delete(id);
    }
}
