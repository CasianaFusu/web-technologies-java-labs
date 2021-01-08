package com.proiect.biblioteca.mapper;

import com.proiect.biblioteca.domain.Utilizator;
import com.proiect.biblioteca.dto.UtilizatorDto;
import org.mapstruct.Mapper;

@Mapper
public interface UtilizatorMapper extends EntityMapper<UtilizatorDto, Utilizator>{
}
