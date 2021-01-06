package com.proiect.biblioteca.mapper;

import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.dto.CategorieDto;
import com.proiect.biblioteca.dto.ImprumutDto;
import org.mapstruct.Mapper;

@Mapper
public interface ImprumutMapper extends EntityMapper<ImprumutDto, Imprumut> {
}
