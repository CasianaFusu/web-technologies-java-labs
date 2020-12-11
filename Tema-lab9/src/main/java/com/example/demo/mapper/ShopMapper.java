package com.example.demo.mapper;

import com.example.demo.domain.Owner;
import com.example.demo.domain.Shop;
import com.example.demo.dto.OwnerDto;
import com.example.demo.dto.ShopDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "string")
public interface ShopMapper extends EntityMapper<ShopDto, Shop>  {
}
