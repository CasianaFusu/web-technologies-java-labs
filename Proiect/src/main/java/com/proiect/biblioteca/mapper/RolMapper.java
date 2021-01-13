package com.proiect.biblioteca.mapper;

import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.domain.Rol;
import com.proiect.biblioteca.dto.CategorieDto;
import com.proiect.biblioteca.dto.RolDto;
import org.mapstruct.Mapper;

@Mapper
public interface RolMapper extends EntityMapper<RolDto, Rol>{
}
