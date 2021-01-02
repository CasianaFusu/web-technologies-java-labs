package com.proiect.biblioteca.mapper;

import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.dto.CarteDto;
import org.mapstruct.Mapper;

@Mapper
public interface CarteMapper extends EntityMapper<CarteDto, Carte> {

}

