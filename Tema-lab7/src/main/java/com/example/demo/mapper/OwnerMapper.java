package com.example.demo.mapper;


import com.example.demo.domain.Owner;
import com.example.demo.dto.OwnerDto;
import org.mapstruct.Mapper;

@Mapper(uses = ShopMapper.class)
public interface OwnerMapper {
    OwnerDto mapToDto(Owner entity);
    Owner mapToEntity(OwnerDto dto);
}
