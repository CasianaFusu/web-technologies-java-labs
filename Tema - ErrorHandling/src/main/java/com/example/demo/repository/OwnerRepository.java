package com.example.demo.repository;

import com.example.demo.domain.Owner;
import com.example.demo.domain.Shop;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.OwnerNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OwnerRepository {
    private final List<Owner> ownerList = new ArrayList<>();

    @Autowired
    private OwnerRepository ownerRepository;

    public OwnerRepository(){
        initOwnerList();
    }

    public Owner get(String cnp){
        return ownerList.stream()
                .filter(owner -> owner.getCnp().equals(cnp))
                .findAny()
                .orElseThrow(() -> new OwnerNotFoundException(String.format("Nu a fost gasit un Proprietar cu acest CNP", cnp)));
    }

    public List<Owner> getAll(){
        return ownerList;
    }

    public Owner save(Owner request){
        ownerList.add(request);
        return request;
    }

    public String delete(String cnp){
        Owner owner = get(cnp);
        ownerList.remove(owner);
        return "Proprietarul a fost sters";
    }

    /*
    public Owner update(Owner request){
        Optional<Owner> ownerOptional = get(request.getCnp());
        if(ownerOptional.isPresent()){
            ownerList.remove(ownerOptional.get());
            ownerList.add(request);
            return get(request.getCnp()).get();
        }
        return null;
    }*/

    private void initOwnerList(){
        ownerList.add(Owner.builder()
                .cnp("2023030322255")
                .nume("Gina Popescu")
                .shop(new Shop ("43534534","Lidl"))
                .build());

        ownerList.add(Owner.builder()
                .cnp("6748397638675")
                .nume("Paul Bogdann")
                .shop(new Shop("534534534534","Mega"))
                .build());
    }
}
