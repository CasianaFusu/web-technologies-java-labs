package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Autor;
import com.proiect.biblioteca.domain.Utilizator;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import com.proiect.biblioteca.exception.PropertyNotGoodException;
import com.proiect.biblioteca.repository.ImprumutRepository;
import com.proiect.biblioteca.repository.RolRepository;
import com.proiect.biblioteca.repository.UtilizatorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilizatorValidatorService {
    private final UtilizatorRepository utilizatorRepository;
    private final ImprumutRepository imprumutRepository;
    private final RolRepository rolRepository;

    public UtilizatorValidatorService(RolRepository rolRepository, UtilizatorRepository utilizatorRepository
    ,ImprumutRepository imprumutRepository){
        this.utilizatorRepository = utilizatorRepository;
        this.imprumutRepository = imprumutRepository;
        this.rolRepository = rolRepository;
    }
    public int validateRequest(Utilizator request) {
        String pattern = "[A-Za-z]*";

        if (!request.getNume().matches(pattern)) {
            throw new PropertyNotGoodException("Nume", "contine caractere ilegale!");
        }

        if (!request.getPrenume().matches(pattern)) {
            throw new PropertyNotGoodException("Prenume", "contine caractere ilegale!");
        }

        var rol = rolRepository.findById(request.getIdRol());
        if(!rol.isPresent())
        {
            throw new EntityNotFoundException("idRol");
        }
        return 0;
    }

    public int validateRequestActivare(int requestId){
        var result = utilizatorRepository.findById(requestId);
        if(result.isPresent()){
           if(result.get().isActivat() == true){
               throw new PropertyNotGoodException("Activare", "Contul a fost deja activat");
           }
        }
        return 0;
    }

    public int validateRequestBeforeDelete(int id){

        var result = imprumutRepository.findAllByIdUtilizator(id);
        if(result.size()>0)
        {
            throw new BadRequestException("Acest utilizator are imprumuturi active!");
        }
        return 0;
    }
}
