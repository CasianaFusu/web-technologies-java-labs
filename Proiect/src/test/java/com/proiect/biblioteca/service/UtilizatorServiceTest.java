package com.proiect.biblioteca.service;


import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Utilizator;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.ImprumutRepository;
import com.proiect.biblioteca.repository.UtilizatorRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UtilizatorServiceTest {
    @Mock
    private UtilizatorRepository utilizatorRepository;

    @Mock
    private UtilizatorValidatorService validator;

    @InjectMocks
    private UtilizatorService service;
    private Utilizator expected;

    private int id;
    private String username;
    private String nume;
    private String prenume;
    private String email;
    private String parola;
    private boolean activat;
    private int idRol;

    @BeforeEach
    void setUp() {
        expected = Utilizator.builder()
                .id(11)
                .username(RandomStringUtils.randomAlphabetic(30))
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .email(RandomStringUtils.randomAlphabetic(30))
                .parola(RandomStringUtils.randomAlphabetic(30))
                .activat(false)
                .idRol(2)
                .build();
    }

    @Test
    void test_getAll(){
        //Arrange
        List<Utilizator> expected = new ArrayList<>();
        expected.add(Utilizator.builder()
                .id(11)
                .username(RandomStringUtils.randomAlphabetic(30))
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .email(RandomStringUtils.randomAlphabetic(30))
                .parola(RandomStringUtils.randomAlphabetic(30))
                .activat(false)
                .idRol(2)
                .build());

        when(utilizatorRepository.findAll()).thenReturn(expected);

        //Act

        List<Utilizator> result = service.getAll();

        //Assert

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_update(){
        //Arange
        Utilizator request = Utilizator.builder()
                .id(11)
                .username(RandomStringUtils.randomAlphabetic(30))
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .email(RandomStringUtils.randomAlphabetic(30))
                .parola(RandomStringUtils.randomAlphabetic(30))
                .activat(false)
                .idRol(2)
                .build();

        int affectedRows = 1;

        when(utilizatorRepository.update(request)).thenReturn(affectedRows);

        //Act
        int result = service.update(request);

        //Assert
        assertThat(result).isEqualTo(affectedRows);
    }

    @Test
    void test_create() {
        //Arange
        Utilizator request = Utilizator.builder()
                .username(RandomStringUtils.randomAlphabetic(30))
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .email(RandomStringUtils.randomAlphabetic(30))
                .parola(RandomStringUtils.randomAlphabetic(30))
                .activat(false)
                .idRol(2)
                .build();

        when(utilizatorRepository.create(request)).thenReturn(expected);

        //Act
        Utilizator result = service.create(request);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_delete(){
        //Arange

        int requestId = 10;
        String expected = "Utilizatorul a fost sters.";

        when(validator.validateRequestBeforeDelete(requestId)).thenReturn(0);
        when(utilizatorRepository.delete(requestId)).thenReturn(expected);

        //Act

        String result = service.delete(requestId);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_delete_when_validator_throws_exception() {
        //Arange

        int requestId = 10;

        when(validator.validateRequestBeforeDelete(requestId)).thenThrow(BadRequestException.class);

        //Act

        //Assert
        assertThrows(BadRequestException.class, ()->service.delete(requestId));
    }

    @Test
    void test_activate(){
        //Arrange
        int requestId = 10;
        int affectedRows = 1;

        when(utilizatorRepository.activate(requestId)).thenReturn(affectedRows);

        //Act

        int result = service.activate(requestId);

        //Assert

        assertThat(result).isEqualTo(affectedRows);
    }

    @Test
    void test_findById()
    {
        //Arrange
        int requestId = 10;
        Optional<Utilizator> expected = Optional.ofNullable(Utilizator.builder()
                .id(11)
                .username(RandomStringUtils.randomAlphabetic(30))
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .email(RandomStringUtils.randomAlphabetic(30))
                .parola(RandomStringUtils.randomAlphabetic(30))
                .activat(false)
                .idRol(2)
                .build());

        when(utilizatorRepository.findById(requestId)).thenReturn(expected);

        //Act

        Utilizator result = service.findById(requestId);

        //Assert

        assertThat(result).isEqualTo(expected.get());
    }
}