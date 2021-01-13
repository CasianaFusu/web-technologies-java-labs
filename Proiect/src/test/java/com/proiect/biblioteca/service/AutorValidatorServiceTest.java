package com.proiect.biblioteca.service;

import com.proiect.biblioteca.domain.Autor;
import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.exception.BadRequestException;
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
class AutorValidatorServiceTest {
    @Mock
    private CarteRepository carteRepository;

    @Mock
    private AutorRepository autorRepository;

    @InjectMocks
    private AutorValidatorService service;


    @Test
    void test_validateRequest() {
        //Arange
        Autor request = Autor.builder()
                .id(2)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .build();


        Optional<Autor> expectedAutor = Optional.empty();

        int expected = 0;

        when(autorRepository.findByName(request.getNume(), request.getPrenume())).thenReturn(expectedAutor);

        //Act

        int result = service.validateRequest(request);

        //Assert
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void test_validateRequest_when_validator_throws_exception() {
        //Arange
        Autor request = Autor.builder()
                .id(2)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .build();

        when(autorRepository.findByName(request.getNume(), request.getPrenume())).thenThrow(BadRequestException.class);

        //Act

        //Assert
        assertThrows(BadRequestException.class, () -> service.validateRequest(request));
    }

    @Test
    void test_delete() {
        //Arange

        int requestId = 10;
        int expected = 0;

       List<Carte> expectedCarte = new ArrayList();;

        when(carteRepository.findByIdAutor(requestId)).thenReturn(expectedCarte);

        //Act

        int result = service.validateDelete(requestId);

        //Assert
        assertThat(result).isEqualTo(expected);    }

    @Test
    void test_delete_throws_bad_request_exception() {
        //Arange
        int requestId = 10;

        when(carteRepository.findByIdAutor(requestId)).thenThrow(BadRequestException.class);

        //Act

        //Assert
        assertThrows(BadRequestException.class, () -> service.validateDelete(requestId));
    }
}