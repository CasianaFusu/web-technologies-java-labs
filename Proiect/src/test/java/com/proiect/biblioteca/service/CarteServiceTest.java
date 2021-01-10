package com.proiect.biblioteca.service;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarteServiceTest {
    @Mock
    private CarteRepository carteRepository;

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

        when(carteRepository.create(request)).thenReturn(expected);

        //Act
        Carte result = service.create(request);

        //Assert
        assertThat(result).isEqualTo(expected);
    }
}