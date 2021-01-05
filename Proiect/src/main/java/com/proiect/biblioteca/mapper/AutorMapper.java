package com.proiect.biblioteca.mapper;

import com.proiect.biblioteca.domain.Autor;
import com.proiect.biblioteca.dto.AutorDto;
import org.mapstruct.Mapper;

@Mapper
public interface AutorMapper extends EntityMapper<AutorDto, Autor> {
}
