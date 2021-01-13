package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import com.proiect.biblioteca.exception.PropertyNotGoodException;
import com.proiect.biblioteca.repository.ImprumutRepository;
import org.apache.commons.lang3.RandomStringUtils;
import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.repository.CarteRepository;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarteServiceTest {
    @Mock
    private CarteRepository carteRepository;

    @Mock
    private ImprumutRepository imprumutRepository;

    @Mock
    private CarteValidatorService validatorService;

    @InjectMocks
    private CarteService service;

    private Carte expected;

    @BeforeEach
    void setUp() {
        expected = Carte.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .idAutor(20)
                .isbn(RandomStringUtils.randomAlphabetic(13))
                .dataAdaugare(new Date())
                .idCategorie(12)
                .stoc(10)
                .build();
    }

    @Test
    void test_getAll(){
        //Arrange
        List<Carte> expected = new ArrayList<>();
        expected.add(Carte.builder()
                .nume(RandomStringUtils.randomAlphabetic(30))
                .idAutor(20)
                .isbn(RandomStringUtils.randomAlphabetic(13))
                .dataAdaugare(new Date())
                .idCategorie(12)
                .stoc(10)
                .build());
        when(carteRepository.findAll()).thenReturn(expected);

        //Act

        List<Carte> result = service.getAll();

        //Assert

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_update(){
        //Arange
        Carte request = Carte.builder()
                .nume(RandomStringUtils.randomAlphabetic(30))
                .idAutor(20)
                .isbn(RandomStringUtils.randomAlphabetic(13))
                .dataAdaugare(new Date())
                .idCategorie(12)
                .stoc(10)
                .build();

        int affectedRows = 1;
        when(validatorService.validateRequest(request)).thenReturn(0);
        when(carteRepository.update(request)).thenReturn(affectedRows);

        //Act
        int result = service.update(request);

        //Assert
        assertThat(result).isEqualTo(affectedRows);
    }

    @Test
    void test_create() {
        //Arange
        Carte request = Carte.builder()
                .nume(RandomStringUtils.randomAlphabetic(30))
                .idAutor(20)
                .isbn(RandomStringUtils.randomAlphabetic(13))
                .dataAdaugare(new Date())
                .idCategorie(12)
                .stoc(10)
                .build();
        when(validatorService.validateRequest(request)).thenReturn(0);
        when(carteRepository.create(request)).thenReturn(expected);

        //Act
        Carte result = service.create(request);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_delete(){
        //Arange

        int requestId = 10;
        String expected = "Cartea a fost stearsa.";

        when(validatorService.validateDelete(requestId)).thenReturn(0);
        when(carteRepository.delete(requestId)).thenReturn(expected);

        //Act

        String result = service.delete(requestId);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_delete_when_validator_throws_exception() {
        //Arange

        int requestId = 10;

        when(validatorService.validateDelete(requestId)).thenThrow(BadRequestException.class);

        //Act

        //Assert
        assertThrows(BadRequestException.class, ()->service.delete(requestId));
    }

    @Test
    void test_findById()
    {
        //Arrange
        int requestId = 10;
        Optional<Carte> expected = Optional.ofNullable(Carte.builder()
                .nume(RandomStringUtils.randomAlphabetic(30))
                .idAutor(20)
                .isbn(RandomStringUtils.randomAlphabetic(13))
                .dataAdaugare(new Date())
                .idCategorie(12)
                .stoc(10)
                .build());

        when(carteRepository.findById(requestId)).thenReturn(expected);

        //Act

        Carte result = service.findById(requestId);

        //Assert

        assertThat(result).isEqualTo(expected.get());

    }
}