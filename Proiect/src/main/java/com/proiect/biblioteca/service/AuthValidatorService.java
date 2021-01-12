package com.proiect.biblioteca.service;

import com.proiect.biblioteca.exception.NotAuthorizedException;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.ImprumutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthValidatorService {


    private final UtilizatorService utilizatorService;

    @Autowired
    public AuthValidatorService(
                                UtilizatorService utilizatorService) {

        this.utilizatorService = utilizatorService;
    }

    public int ValidateBibliotecar(int idUtilizatorAutentificat)  {
        var user = utilizatorService.findById(idUtilizatorAutentificat);
        if(user.getIdRol() != 1)
        {
            throw new NotAuthorizedException("Nu sunteti autorizat sa efectuati aceast request!");

        }
        return 0;
   }


}
