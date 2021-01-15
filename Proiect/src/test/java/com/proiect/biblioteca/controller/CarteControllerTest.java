package com.proiect.biblioteca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.dto.CarteDto;
import com.proiect.biblioteca.mapper.CarteMapper;
import com.proiect.biblioteca.service.CarteService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CarteController.class)
class CarteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarteService carteService;

    @MockBean
    private CarteMapper carteMapper;

    @Test
    void test_create() throws Exception {
        // Arrange
        CarteDto request = CarteDto.builder()
                .nume(RandomStringUtils.randomAlphabetic(30))
                .idAutor(20)
                .isbn(RandomStringUtils.randomNumeric(13))
                .dataAdaugare(new Date())
                .idCategorie(12)
                .stoc(10)
                .build();

        Carte created = Carte.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .idAutor(20)
                .isbn(RandomStringUtils.randomNumeric(13))
                .dataAdaugare(new Date())
                .idCategorie(12)
                .stoc(10)
                .build();

        when(carteService.create(any(Carte.class))).thenReturn(created);

        // Act
        mockMvc.perform(post("/carti/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        // Assert
    }
    @Test
    void test_findById() throws Exception {
        // Arrange
        int  request = 10;

        Carte returned = Carte.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .idAutor(20)
                .isbn(RandomStringUtils.randomAlphabetic(13))
                .dataAdaugare(new Date())
                .idCategorie(12)
                .stoc(10)
                .build();

        when(carteService.findById(request)).thenReturn(returned);

        // Act
        mockMvc.perform(get("/carti/"+request)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }

    @Test
    void test_getAll() throws Exception {
        // Arrange


        List<Carte> returned =  new ArrayList<Carte>( );
        returned.add(Carte.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .idAutor(20)
                .isbn(RandomStringUtils.randomAlphabetic(13))
                .dataAdaugare(new Date())
                .idCategorie(12)
                .stoc(10)
                .build());

        when(carteService.getAll()).thenReturn(returned);

        // Act
        mockMvc.perform(get("/carti")
                )
                .andExpect(status().isOk());

        // Assert
    }
    @Test
    void test_delete() throws Exception {
        // Arrange
        int  request = 10;
        String expected = "Cartea a fost stearsa cu succes!";


        when(carteService.delete(request)).thenReturn(expected);

        // Act
        mockMvc.perform(delete("/carti/delete/" +request)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted());

        // Assert
    }

    @Test
    void test_update() throws Exception {
        // Arrange
        CarteDto request = CarteDto.builder()
                .nume(RandomStringUtils.randomAlphabetic(30))
                .idAutor(20)
                .isbn(RandomStringUtils.randomNumeric(13))
                .dataAdaugare(new Date())
                .idCategorie(12)
                .stoc(10)
                .build();

        Carte created = Carte.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .idAutor(20)
                .isbn(RandomStringUtils.randomNumeric(13))
                .dataAdaugare(new Date())
                .idCategorie(12)
                .stoc(10)
                .build();

        when(carteService.create(any(Carte.class))).thenReturn(created);

        // Act
        mockMvc.perform(put("/carti/update/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }


    @Test
    void test_getCartiByAutor() throws Exception {
        // Arrange
        int  request = 10;

        List<Carte> returned =  new ArrayList<Carte>( );
        returned.add(Carte.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .idAutor(20)
                .isbn(RandomStringUtils.randomAlphabetic(13))
                .dataAdaugare(new Date())
                .idCategorie(12)
                .stoc(10)
                .build());

        when(carteService.getCartiByAutor(request)).thenReturn(returned);

        // Act
        mockMvc.perform(get("/carti/getbyauthor/"+request)
                )
                .andExpect(status().isOk());

        // Assert
    }
    @Test
    void test_getCarteByName() throws Exception {
        // Arrange
        String  request = RandomStringUtils.randomAlphabetic(20);

        Carte returned = Carte.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .idAutor(20)
                .isbn(RandomStringUtils.randomAlphabetic(13))
                .dataAdaugare(new Date())
                .idCategorie(12)
                .stoc(10)
                .build();

        when(carteService.getCarteByName(request)).thenReturn(returned);

        // Act
        mockMvc.perform(get("/carti/getbyname/"+request))
                .andExpect(status().isOk());

        // Assert
    }


}