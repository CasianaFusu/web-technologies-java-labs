package com.example.demo.mapper;

import com.example.demo.domain.Owner;
import com.example.demo.dto.OwnerDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-23T17:03:46+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
public class ShopMapperImpl implements ShopMapper {

    @Override
    public OwnerDto mapToDto(Owner entity) {
        if ( entity == null ) {
            return null;
        }

        OwnerDto ownerDto = new OwnerDto();

        ownerDto.setNume( entity.getNume() );
        ownerDto.setShop( entity.getShop() );

        return ownerDto;
    }

    @Override
    public Owner mapToEntity(OwnerDto dto) {
        if ( dto == null ) {
            return null;
        }

        Owner owner = new Owner();

        owner.setNume( dto.getNume() );
        owner.setShop( dto.getShop() );

        return owner;
    }
}
