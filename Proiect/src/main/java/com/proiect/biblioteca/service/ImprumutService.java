package com.proiect.biblioteca.service;


import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.ImprumutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

//TODO: este bine cum am facut la stocul 0?
//TODO: la update poate face update la nr de carti imprumutate??
//TODO: dataexpirare dataterminare de verificat
@Service
public class ImprumutService {
    private final ImprumutRepository imprumutRepository;
    private final CarteRepository carteRepository;

    private final ImprumutValidatorService validator;
    private final AuthValidatorService authValidatorService;

    public ImprumutService(ImprumutRepository imprumutRepository,
                           CarteRepository carteRepository,
                            ImprumutValidatorService validator,
                            AuthValidatorService authValidatorService){
        this.imprumutRepository = imprumutRepository;
        this.carteRepository = carteRepository;
        this.validator = validator;
        this.authValidatorService = authValidatorService;
    }

    public List<Imprumut> getAll(){
        return imprumutRepository.findAll();
    }

    public List<Imprumut> getImprumuturiNeincheiate ()
    {
        return imprumutRepository.findAllNeincheiate();
    }

    @Transactional
    public Imprumut create(Imprumut request,int idUtilizatorAutentificat){
        authValidatorService.ValidateBibliotecar(idUtilizatorAutentificat);

        if (validator.validateRequest(request) ==0)
        {
            Imprumut imprumut = imprumutRepository.create(request);
            int result = carteRepository.decreaseStocById(request.getIdCarte());
            return imprumut;
        }
        return null;
    }

    @Transactional
    public String delete(int id,int idUtilizatorAutentificat){
        authValidatorService.ValidateBibliotecar(idUtilizatorAutentificat);
        Optional<Imprumut> imprumut = imprumutRepository.findById(id);

        if(imprumut.isPresent())
        {
            String resultImprumut = imprumutRepository.delete(id);
            int result = carteRepository.increaseStocById(imprumut.get().getIdCarte());
            return resultImprumut;
        }
        else
            throw new BadRequestException("Nu există acest un imprumut pentru această carte");
    }

    public Imprumut getById(int id){

        return imprumutRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Imprumut"));
    }

    public int update(Imprumut request,int idUtilizatorAutentificat){
        authValidatorService.ValidateBibliotecar(idUtilizatorAutentificat);

        validator.validateRequestBeforeUpdate(request);


        return imprumutRepository.update(request);
    }
}
