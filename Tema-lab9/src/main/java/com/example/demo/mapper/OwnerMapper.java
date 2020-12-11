package com.example.demo.mapper;


import com.example.demo.domain.Owner;
import com.example.demo.dto.OwnerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "string")
public interface OwnerMapper extends EntityMapper<OwnerDto, Owner> {
}
