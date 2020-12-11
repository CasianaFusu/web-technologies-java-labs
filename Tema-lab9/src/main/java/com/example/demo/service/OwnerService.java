package com.example.demo.service;
import com.example.demo.domain.Owner;
import com.example.demo.dto.OwnerDto;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.OwnerNotFoundException;
import com.example.demo.mapper.OwnerMapper;
import com.example.demo.repository.OwnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public Owner get(String cnp) {
        return ownerRepository.get(cnp)
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Nu a fost gasit niciun owner cu acest cnp.")));
    }

    public List<Owner> getAll() {
        return ownerRepository.getAll();
    }

    public void create(Owner owner) {
        ownerRepository.save(owner);
    }

    private void validateRequest(OwnerDto request){
        if (ownerRepository.getAll().stream()
                .anyMatch(owner -> owner.getCnp().equals(request.getCnp()))) {
            throw new BadRequestException("Exista un proprietar cu acest CNP");
        }
    }

   public void update(String cnp, String nume) {
        ownerRepository.update(cnp,nume);
    }

    public String delete(String cnp) {
        return ownerRepository.delete(cnp);
    }
}
