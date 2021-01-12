package com.proiect.biblioteca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proiect.biblioteca.domain.Autor;
import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.dto.AutorDto;
import com.proiect.biblioteca.dto.CarteDto;
import com.proiect.biblioteca.mapper.AutorMapper;
import com.proiect.biblioteca.mapper.CarteMapper;
import com.proiect.biblioteca.service.AutorService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AutorController.class)
class AutorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AutorService autorService;

    @MockBean
    private AutorMapper autorMapper;

    @Test
    void test_getAll() throws Exception {
        // Arrange


        List<Autor> returned =  new ArrayList<Autor>( );
        returned.add(Autor.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .build());

        when(autorService.getAll()).thenReturn(returned);

        // Act
        mockMvc.perform(get("/autori")
        )
                .andExpect(status().isOk());

        // Assert
    }
    @Test
    void test_create() throws Exception {
        // Arrange
        AutorDto request = AutorDto.builder()
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .build();

        Autor created = Autor.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .build();

        when(autorService.create(any(Autor.class))).thenReturn(created);

        // Act
        mockMvc.perform(post("/autori/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        // Assert
    }
    @Test
    void test_getById() throws Exception {
        // Arrange
        int  request = 10;

        Autor returned = Autor.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .build();

        when(autorService.findById(request)).thenReturn(returned);

        // Act
        mockMvc.perform(get("/autori/"+request)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }
    @Test
    void test_delete() throws Exception {
        // Arrange
        int  request = 10;
        String expected = "Autorul a fost sters cu succes!";


        when(autorService.delete(request)).thenReturn(expected);

        // Act
        mockMvc.perform(delete("/autori/delete/" +request)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted());

        // Assert
    }

    @Test
    void test_update() throws Exception {
        // Arrange
        AutorDto request = AutorDto.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .build();

        Autor created = Autor.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .build();

        when(autorService.create(any(Autor.class))).thenReturn(created);

        // Act
        mockMvc.perform(put("/autori/update/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }

}