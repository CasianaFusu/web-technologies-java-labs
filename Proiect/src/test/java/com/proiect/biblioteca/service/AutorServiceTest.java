package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Autor;
import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.exception.BadRequestException;
import com.proiect.biblioteca.exception.EntityNotFoundException;
import com.proiect.biblioteca.exception.PropertyNotGoodException;
import com.proiect.biblioteca.repository.AutorRepository;
import com.proiect.biblioteca.repository.CarteRepository;
import com.proiect.biblioteca.repository.ImprumutRepository;
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
class AutorServiceTest {
    @Mock
    private CarteRepository carteRepository;

    @Mock
    private AutorRepository autorRepository;

    @Mock
    private AutorValidatorService validatorService;

    @InjectMocks
    private AutorService service;
    private Autor expected;

    @BeforeEach
    void setUp() {
        expected = Autor.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(15))
                .prenume(RandomStringUtils.randomAlphabetic(15))
                .build();
    }

    @Test
    void test_getAll(){
        //Arrange
        List<Autor> expected = new ArrayList<>();
        expected.add(Autor.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(15))
                .build());

        when(autorRepository.findAll()).thenReturn(expected);

        //Act

        List<Autor> result = service.getAll();

        //Assert

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_update(){
        //Arange
        Autor request = Autor.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(15))
                .build();

        int affectedRows = 1;
        when(validatorService.validateRequest(request)).thenReturn(0);
        when(autorRepository.update(request)).thenReturn(affectedRows);

        //Act
        int result = service.update(request);

        //Assert
        assertThat(result).isEqualTo(affectedRows);
    }

    @Test
    void test_create() {
        //Arange
        Autor request = Autor.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(15))
                .build();

        when(validatorService.validateRequest(request)).thenReturn(0);
        when(autorRepository.create(request)).thenReturn(expected);

        //Act
        Autor result = service.create(request);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_delete(){
        //Arange

        int requestId = 10;
        String expected = "Autorul a fost sters.";

        when(validatorService.validateDelete(requestId)).thenReturn(0);
        when(autorRepository.delete(requestId)).thenReturn(expected);

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
        Optional<Autor> expected = Optional.ofNullable(Autor.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(15))
                .build());

        when(autorRepository.findById(requestId)).thenReturn(expected);

        //Act

        Autor result = service.findById(requestId);

        //Assert

        assertThat(result).isEqualTo(expected.get());

    }
}