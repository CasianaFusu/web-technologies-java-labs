package com.example.demo.mapper;

import com.example.demo.domain.Owner;
import com.example.demo.dto.OwnerDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-02T20:22:49+0200",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
public class ShopMapperImpl implements ShopMapper {

    @Override
    public OwnerDto mapToDto(Owner entity) {
        if ( entity == null ) {
            return null;
        }

        OwnerDto ownerDto = new OwnerDto();

        ownerDto.setCnp( entity.getCnp() );
        ownerDto.setHasShop( entity.getHasShop() );
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

        owner.setCnp( dto.getCnp() );
        owner.setHasShop( dto.getHasShop() );
        owner.setNume( dto.getNume() );
        owner.setShop( dto.getShop() );

        return owner;
    }
}
