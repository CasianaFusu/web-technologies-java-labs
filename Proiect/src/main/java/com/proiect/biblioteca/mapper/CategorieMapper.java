package com.proiect.biblioteca.mapper;

import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.dto.CategorieDto;
import org.mapstruct.Mapper;

@Mapper
public interface CategorieMapper extends EntityMapper<CategorieDto, Categorie>{
}
