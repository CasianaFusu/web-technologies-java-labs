package com.proiect.biblioteca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Utilizator;
import com.proiect.biblioteca.dto.CarteDto;
import com.proiect.biblioteca.dto.UtilizatorDto;
import com.proiect.biblioteca.mapper.CarteMapper;
import com.proiect.biblioteca.mapper.UtilizatorMapper;
import com.proiect.biblioteca.service.CarteService;
import com.proiect.biblioteca.service.UtilizatorService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UtilizatorController.class)
class UtilizatorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UtilizatorService utilizatorService;

    @MockBean
    private UtilizatorMapper utilizatorMapper;

    @Test
    void test_create() throws Exception {
        // Arrange
        UtilizatorDto request = UtilizatorDto.builder()
                .username(RandomStringUtils.randomAlphabetic(30))
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .email("test@gmail.com")
                .parola(RandomStringUtils.randomAlphabetic(30))
                .activat(false)
                .idRol(2)
                .build();

        Utilizator created = Utilizator.builder()
                .id(11)
                .username(RandomStringUtils.randomAlphabetic(30))
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .email("test@gmail.com")
                .parola(RandomStringUtils.randomAlphabetic(30))
                .activat(false)
                .idRol(2)
                .build();

        when(utilizatorService.create(any(Utilizator.class))).thenReturn(created);

        // Act
        mockMvc.perform(post("/utilizatori/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        // Assert
    }
    @Test
    void test_findById() throws Exception {
        // Arrange
        int  request = 10;

        Utilizator returned = Utilizator.builder()
                .id(11)
                .username(RandomStringUtils.randomAlphabetic(30))
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .email("test@gmail.com")
                .parola(RandomStringUtils.randomAlphabetic(30))
                .activat(false)
                .idRol(2)
                .build();

        when(utilizatorService.findById(request)).thenReturn(returned);

        // Act
        mockMvc.perform(get("/utilizatori/"+request)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }

    @Test
    void test_getAll() throws Exception {
        // Arrange

        List<Utilizator> returned =  new ArrayList<Utilizator>( );
        returned.add(Utilizator.builder()
                .id(11)
                .username(RandomStringUtils.randomAlphabetic(30))
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .email("test@gmail.com")
                .parola(RandomStringUtils.randomAlphabetic(30))
                .activat(false)
                .idRol(2)
                .build());

        when(utilizatorService.getAll()).thenReturn(returned);

        // Act
        mockMvc.perform(get("/utilizatori"))
                .andExpect(status().isOk());

        // Assert
    }
    @Test
    void test_delete() throws Exception {
        // Arrange
        int  request = 10;
        String expected = "Utilizatorul a fost sters cu succes!";

        when(utilizatorService.delete(request)).thenReturn(expected);

        // Act
        mockMvc.perform(delete("/utilizatori/delete/" +request)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted());

        // Assert
    }

    @Test
    void test_update() throws Exception {
        // Arrange
        UtilizatorDto request = UtilizatorDto.builder()
                .username(RandomStringUtils.randomAlphabetic(30))
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .email("test@gmail.com")
                .parola(RandomStringUtils.randomAlphabetic(30))
                .activat(false)
                .idRol(2)
                .build();

        Utilizator created = Utilizator.builder()
                .id(11)
                .username(RandomStringUtils.randomAlphabetic(30))
                .nume(RandomStringUtils.randomAlphabetic(30))
                .prenume(RandomStringUtils.randomAlphabetic(30))
                .email("test@gmail.com")
                .parola(RandomStringUtils.randomAlphabetic(30))
                .activat(false)
                .idRol(2)
                .build();

        when(utilizatorService.create(any(Utilizator.class))).thenReturn(created);

        // Act
        mockMvc.perform(put("/utilizatori/update/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted());

        // Assert
    }

    @Test
    void test_activate() throws Exception {
        //Arrange

        int  request = 10;
        int affectedRows = 1;

        when(utilizatorService.activate(request)).thenReturn(affectedRows);

        //Act
        mockMvc.perform(put("/utilizatori/activate/" +request)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }
}
