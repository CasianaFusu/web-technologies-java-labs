package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Autor;
import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import com.proiect.biblioteca.exception.PropertyNotGoodException;
import com.proiect.biblioteca.repository.AutorRepository;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.CategorieRepository;
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
class CategorieServiceTest {
    @Mock
    private CarteRepository carteRepository;

    @Mock
    private CategorieRepository categorieRepository;

    @Mock
    private CategorieValidatorService validatorService;

    @InjectMocks
    private CategorieService service;
    private Categorie expected;

    @BeforeEach
    void setUp() {
        expected = Categorie.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(15))
                .build();
    }

    @Test
    void test_getAll() {
        //Arrange
        List<Categorie> expected = new ArrayList<>();
        expected.add(Categorie.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .build());

        when(categorieRepository.findAll()).thenReturn(expected);

        //Act

        List<Categorie> result = service.getAll();

        //Assert

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_update() {
        //Arange
        Categorie request = Categorie.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(15))
                .build();

        int affectedRows = 1;
        when(validatorService.validateRequest(request)).thenReturn(0);
        when(categorieRepository.update(request)).thenReturn(affectedRows);

        //Act
        int result = service.update(request);

        //Assert
        assertThat(result).isEqualTo(affectedRows);
    }

    @Test
    void test_create() {
        //Arange
        Categorie request = Categorie.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(15))
                .build();

        when(validatorService.validateRequest(request)).thenReturn(0);
        when(categorieRepository.create(request)).thenReturn(expected);

        //Act
        Categorie result = service.create(request);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_delete(){
        //Arange

        int requestId = 10;
        String expected = "Categoria a fost stearsa.";

        when(validatorService.validateDelete(requestId)).thenReturn(0);
        when(categorieRepository.delete(requestId)).thenReturn(expected);

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
    void test_findById() {
        //Arrange
        int requestId = 10;
        Optional<Categorie> expected = Optional.ofNullable(Categorie.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(15))
                .build());

        when(categorieRepository.findById(requestId)).thenReturn(expected);

        //Act

        Categorie result = service.findById(requestId);

        //Assert

        assertThat(result).isEqualTo(expected.get());
    }
}