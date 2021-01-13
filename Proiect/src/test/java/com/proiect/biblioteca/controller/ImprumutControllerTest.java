package com.proiect.biblioteca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proiect.biblioteca.domain.Imprumut;
import com.proiect.biblioteca.domain.Utilizator;
import com.proiect.biblioteca.dto.ImprumutDto;
import com.proiect.biblioteca.dto.UtilizatorDto;
import com.proiect.biblioteca.mapper.ImprumutMapper;
import com.proiect.biblioteca.mapper.UtilizatorMapper;
import com.proiect.biblioteca.service.ImprumutService;
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
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ImprumutController.class)
class ImprumutControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ImprumutService imprumutService;

    @MockBean
    private ImprumutMapper imprumutMapper;

    @Test
    void test_create() throws Exception {
        // Arrange
        ImprumutDto request = ImprumutDto.builder()
                .idUtilizatorAutentificat(12)
                .idUtilizator(12)
                .idCarte(12)
                .dataImprumut(new Date(2020-10-9))
                .dataExpirare(new Date(2020-11-1))
                .incheiat(false)
                .build();

        Imprumut created = Imprumut.builder()
                .id(11)
                .idUtilizator(12)
                .idCarte(12)
                .dataImprumut(new Date(2020-10-9))
                .dataExpirare(new Date(2020-11-1))
                .incheiat(false)
                .build();

        when(imprumutService.create(any(Imprumut.class), eq(request.getIdUtilizatorAutentificat()))).thenReturn(created);

        // Act
        mockMvc.perform(post("/imprumuturi/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        // Assert
    }
    @Test
    void test_findById() throws Exception {
        // Arrange
        int  request = 10;

        Imprumut returned = Imprumut.builder()
                .id(11)
                .idUtilizator(12)
                .idCarte(12)
                .dataImprumut(new Date(2020-10-9))
                .dataExpirare(new Date(2020-11-1))
                .incheiat(false)
                .build();

        when(imprumutService.getById(request)).thenReturn(returned);

        // Act
        mockMvc.perform(get("/imprumuturi/"+request)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }

    @Test
    void test_getAll() throws Exception {
        // Arrange

        List<Imprumut> returned =  new ArrayList<Imprumut>( );
        returned.add(Imprumut.builder()
                .id(11)
                .idUtilizator(12)
                .idCarte(12)
                .dataImprumut(new Date(2020-10-9))
                .dataExpirare(new Date(2020-11-1))
                .incheiat(false)
                .build());

        when(imprumutService.getAll()).thenReturn(returned);

        // Act
        mockMvc.perform(get("/imprumuturi"))
                .andExpect(status().isOk());

        // Assert
    }

    @Test
    void test_delete() throws Exception {
        // Arrange
        int  request = 10;
        int idUtilizatorRequest = 2;
        String expected = "Imprumutul a fost sters.!";

        when(imprumutService.delete(request, idUtilizatorRequest)).thenReturn(expected);

        // Act
        mockMvc.perform(delete("/imprumuturi/delete/" +request + "/" +idUtilizatorRequest)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted());

        // Assert
    }

    @Test
    void test_update() throws Exception {
        // Arrange
        ImprumutDto request = ImprumutDto.builder()
                .idUtilizatorAutentificat(12)
                .idUtilizator(12)
                .idCarte(12)
                .dataImprumut(new Date(2020-10-9))
                .dataExpirare(new Date(2020-11-1))
                .incheiat(false)
                .build();

        Imprumut created = Imprumut.builder()
                .id(11)
                .idUtilizator(12)
                .idCarte(12)
                .dataImprumut(new Date(2020-10-9))
                .dataExpirare(new Date(2020-11-1))
                .incheiat(false)
                .build();

        when(imprumutService.create(any(Imprumut.class), eq(request.getIdUtilizatorAutentificat()))).thenReturn(created);

        // Act
        mockMvc.perform(put("/imprumuturi/update/")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isAccepted());

        // Assert
    }

    @Test
    void test_getImprumuturiNeincheiate() throws Exception {
        //Arrange
        List<Imprumut> returned =  new ArrayList<Imprumut>( );
        returned.add(Imprumut.builder()
                .id(11)
                .idUtilizator(12)
                .idCarte(12)
                .dataImprumut(new Date(2020-10-9))
                .dataExpirare(new Date(2020-11-1))
                .incheiat(false)
                .build());

        when(imprumutService.getImprumuturiNeincheiate()).thenReturn(returned);

        //Act
        mockMvc.perform(get("/imprumuturi/getImprumuturiNeincheiate")
                )
                .andExpect(status().isOk());
    }
}
