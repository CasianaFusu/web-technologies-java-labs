package com.proiect.biblioteca.mapper;

import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.domain.Solicitare;
import com.proiect.biblioteca.dto.ImprumutDto;
import com.proiect.biblioteca.dto.SolicitareDto;
import org.mapstruct.Mapper;

@Mapper
public interface SolicitareMapper extends EntityMapper<SolicitareDto, Solicitare> {
}
