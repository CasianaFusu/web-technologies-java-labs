package com.example.demo.service;
import com.example.demo.domain.Owner;
import com.example.demo.dto.OwnerDto;
import com.example.demo.mapper.OwnerMapper;
import com.example.demo.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;
    private final OwnerMapper ownerMapper;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository, OwnerMapper ownerMapper) {
        this.ownerRepository = ownerRepository;
        this.ownerMapper = ownerMapper;
    }

    public OwnerDto get(String cnp) {
        return ownerMapper.mapToDto(ownerRepository.get(cnp).get());
    }

    public List<OwnerDto> getAll() {
        return ownerRepository.getAll().stream()
                .map(person -> ownerMapper.mapToDto(person))
                .collect(Collectors.toList());
    }

    public OwnerDto create(OwnerDto request) {
        Owner savedOwner = ownerRepository.save(ownerMapper.mapToEntity(request));
        return ownerMapper.mapToDto(savedOwner);
    }

    public OwnerDto update(OwnerDto request) {
        Owner updatedOwner = ownerRepository.update(ownerMapper.mapToEntity(request));
        if (updatedOwner != null) {
            return ownerMapper.mapToDto(updatedOwner);
        }
        return null;
    }

    public String delete(String cnp) {
        return ownerRepository.delete(cnp);
    }

}
