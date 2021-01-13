package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.domain.Rol;
import com.proiect.biblioteca.domain.Utilizator;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.repository.CategorieRepository;
import com.proiect.biblioteca.repository.RolRepository;
import com.proiect.biblioteca.repository.UtilizatorRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RolServiceTest {
    @Mock
    private AuthValidatorService authValidatorService;

    @Mock
    private RolRepository rolRepository;

    @Mock
    private UtilizatorRepository utilizatorRepository;


    @InjectMocks
    private RolService service;
    private Rol expected;
    private int utilizatorAutentificat ;

    @BeforeEach
    void setUp() {
        expected = Rol.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(15))
                .build();
        utilizatorAutentificat=1;
    }

    @Test
    void test_getAll() {
        //Arrange
        List<Rol> expected = new ArrayList<>();
        expected.add(Rol.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .build());

        when(rolRepository.findAll()).thenReturn(expected);
        when(authValidatorService.ValidateBibliotecar(utilizatorAutentificat)).thenReturn(0);

        //Act

        List<Rol> result = service.getAll(utilizatorAutentificat);

        //Assert

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_update() {
        //Arange
        Rol request = Rol.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(15))
                .build();

        int affectedRows = 1;
        when(authValidatorService.ValidateBibliotecar(utilizatorAutentificat)).thenReturn(0);
        when(rolRepository.update(request)).thenReturn(affectedRows);

        //Act
        int result = service.update(request,utilizatorAutentificat);

        //Assert
        assertThat(result).isEqualTo(affectedRows);
    }

    @Test
    void test_create() {
        //Arange
        Rol request = Rol.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(15))
                .build();

        when(authValidatorService.ValidateBibliotecar(utilizatorAutentificat)).thenReturn(0);
        when(rolRepository.create(request)).thenReturn(expected);

        //Act
        Rol result = service.create(request,utilizatorAutentificat);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_delete(){
        //Arange

        int requestId = 10;
        String expected = "Rolul a fost sters.";

        var utilizatoriCuRol = new ArrayList<Utilizator>();

        when(authValidatorService.ValidateBibliotecar(utilizatorAutentificat)).thenReturn(0);
        when(utilizatorRepository.findAllByRol(requestId)).thenReturn(utilizatoriCuRol);
        when(rolRepository.delete(requestId)).thenReturn(expected);

        //Act

        String result = service.delete(requestId,utilizatorAutentificat);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_delete_when_validator_throws_exception() {
        //Arange

        int requestId = 10;

        when(authValidatorService.ValidateBibliotecar(utilizatorAutentificat)).thenThrow(BadRequestException.class);

        //Act

        //Assert
        assertThrows(BadRequestException.class, ()->service.delete(requestId,utilizatorAutentificat));
    }

    @Test
    void test_findById() {
        //Arrange
        int requestId = 10;
        Optional<Rol> expected = Optional.ofNullable(Rol.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(15))
                .build());

        when(rolRepository.findById(requestId)).thenReturn(expected);

        //Act

        Rol result = service.findById(requestId,utilizatorAutentificat);

        //Assert

        assertThat(result).isEqualTo(expected.get());
    }
}