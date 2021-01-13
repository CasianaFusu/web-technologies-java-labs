package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Utilizator;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import com.proiect.biblioteca.repository.ImprumutRepository;
import com.proiect.biblioteca.repository.UtilizatorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilizatorValidatorService {
    private final UtilizatorRepository utilizatorRepository;
    private final ImprumutRepository imprumutRepository;

    public UtilizatorValidatorService(UtilizatorRepository utilizatorRepository
    ,ImprumutRepository imprumutRepository){
        this.utilizatorRepository = utilizatorRepository;
        this.imprumutRepository = imprumutRepository;
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
