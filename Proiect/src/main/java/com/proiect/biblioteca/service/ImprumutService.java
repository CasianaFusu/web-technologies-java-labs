package com.proiect.biblioteca.service;


import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.ImprumutRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
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

    public ImprumutService(ImprumutRepository imprumutRepository, CarteRepository carteRepository){
        this.imprumutRepository = imprumutRepository;
        this.carteRepository = carteRepository;
    }

    public List<Imprumut> getAll(){
        return imprumutRepository.findAll();
    }

    @Transactional
    public Imprumut create(Imprumut request){
        Optional<Carte> carte = carteRepository.findById(request.getIdCarte());
        //TODO: tranzactie??
        if(carte.get().getStoc() > 0){
            Imprumut imprumut = imprumutRepository.create(request);
            int result = carteRepository.decreaseStocById(request.getIdCarte());
            return imprumut;
        }
        else throw new RuntimeException("Nu exista carti cu acest id pe stoc");
    }

    @Transactional
    public String delete(int id){
        Optional<Imprumut> imprumut = imprumutRepository.findById(id);

        String resultImprumut = imprumutRepository.delete(id);
        int result = carteRepository.increaseStocById(imprumut.get().getIdCarte());

        return resultImprumut;
    }

    public Imprumut getById(int id){
        return imprumutRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Imprumut"));
    }

    public int update(Imprumut request){
        Optional<Imprumut> imprumut = imprumutRepository.findById(request.getId());

        if(imprumut.get().isIncheiat() == true)
            throw new RuntimeException("Imprumutul a fost incheiat.");
        else {
            if(request.getDataExpirare().before(request.getDataImprumut())){
                throw new RuntimeException("Data de expirare mai mica decat data de imprumut");
            }

            if(request.getDataImprumut().before(new Date())){
                throw new RuntimeException("Data de imprumut este o data din viitor.");
            }

            if (imprumut.get().getIdCarte() != request.getIdCarte()) {
                Optional<Carte> carte = carteRepository.findById(request.getIdCarte());
                if (carte.get().getStoc() > 0) {
                    int resultDecreaseStoc = carteRepository.decreaseStocById(request.getIdCarte());
                    int resultIncreaseStoc = carteRepository.increaseStocById(imprumut.get().getIdCarte());
                } else throw new RuntimeException("Nu mai sunt carti pe stoc cu acest id.");
            }
        }

        return imprumutRepository.update(request);
    }
}
