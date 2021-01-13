package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.domain.Rol;
import com.proiect.biblioteca.dto.RolDto;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.CategorieRepository;
import com.proiect.biblioteca.repository.RolRepository;
import com.proiect.biblioteca.repository.UtilizatorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {
    private final UtilizatorRepository utilizatorRepository;
    private final RolRepository rolRepository;
    private final AuthValidatorService authValidatorService;

    public RolService( UtilizatorRepository utilizatorRepository,
                       RolRepository rolRepository,
                       AuthValidatorService authValidatorService){
        this.utilizatorRepository = utilizatorRepository;
        this.rolRepository = rolRepository;
        this.authValidatorService = authValidatorService;

    }

    public List<Rol> getAll(int idUtilizatorAutentificat){
        authValidatorService.ValidateBibliotecar(idUtilizatorAutentificat);
        return rolRepository.findAll();
    }

    public Rol findById(int id,int idUtilizatorAutentificat){
        authValidatorService.ValidateBibliotecar(idUtilizatorAutentificat);
        return rolRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Rol"));
    }

    public Rol create(Rol request,int idUtilizatorAutentificat){
        authValidatorService.ValidateBibliotecar(idUtilizatorAutentificat);
        return rolRepository.create(request);
    }

    public int update(Rol request,int idUtilizatorAutentificat){
        authValidatorService.ValidateBibliotecar(idUtilizatorAutentificat);
        return rolRepository.update(request);
    }

    public String delete(int id,int idUtilizatorAutentificat){
        authValidatorService.ValidateBibliotecar(idUtilizatorAutentificat);
        var utilizatori = utilizatorRepository.findAllByRol(id);
        if(utilizatori.size()>0)
        {
            throw new BadRequestException("Nu puteti sterge acest rol deoarece exista utilizatori inregistrati cu acest rol");
        }
        return rolRepository.delete(id);
    }
}
