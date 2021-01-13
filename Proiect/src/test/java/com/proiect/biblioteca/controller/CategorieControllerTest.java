package com.proiect.biblioteca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proiect.biblioteca.domain.Carte;
import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.dto.CarteDto;
import com.proiect.biblioteca.dto.CategorieDto;
import com.proiect.biblioteca.mapper.CarteMapper;
import com.proiect.biblioteca.mapper.CategorieMapper;
import com.proiect.biblioteca.service.CarteService;
import com.proiect.biblioteca.service.CategorieService;
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

@WebMvcTest(controllers = CategorieController.class)
class CategorieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CategorieService categorieService;

    @MockBean
    private CategorieMapper categorieMapper;

    @Test
    void test_create() throws Exception {
        // Arrange
        CategorieDto request = CategorieDto.builder()
                .nume(RandomStringUtils.randomAlphabetic(30))
                .build();

        Categorie created = Categorie.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .build();

        when(categorieService.create(any(Categorie.class))).thenReturn(created);

        // Act
        mockMvc.perform(post("/categorii/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }
    @Test
    void test_findById() throws Exception {
        // Arrange
        int  request = 10;

        Categorie returned = Categorie.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .build();

        when(categorieService.findById(request)).thenReturn(returned);

        // Act
        mockMvc.perform(get("/categorii/"+request)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }

    void test_getByName() throws Exception {
        // Arrange
        int  request = 10;

        Categorie returned = Categorie.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .build();

        when(categorieService.findById(request)).thenReturn(returned);

        // Act
        mockMvc.perform(get("/categorii/"+request)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }


    @Test
    void test_getAll() throws Exception {
        // Arrange
        List<Categorie> returned =  new ArrayList<Categorie>();
        returned.add(Categorie.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .build());

        when(categorieService.getAll()).thenReturn(returned);

        // Act
        mockMvc.perform(get("/categorii")
        )
                .andExpect(status().isOk());
        // Assert
    }

    @Test
    void test_delete() throws Exception {
        // Arrange
        int  request = 10;
        String expected = "Categoria a fost stearsa cu succes!";


        when(categorieService.delete(request)).thenReturn(expected);

        // Act
        mockMvc.perform(delete("/categorii/delete/" +request)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }

    @Test
    void test_update() throws Exception {
        // Arrange
        int numberRowsModified = 1;
        CategorieDto request = CategorieDto.builder()
                .nume(RandomStringUtils.randomAlphabetic(30))
                .build();


        when(categorieService.update(any(Categorie.class))).thenReturn(numberRowsModified);

        // Act
        mockMvc.perform(put("/categorii/update/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }

}