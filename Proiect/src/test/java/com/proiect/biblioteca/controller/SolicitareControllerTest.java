package com.proiect.biblioteca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.domain.Solicitare;
import com.proiect.biblioteca.dto.CategorieDto;
import com.proiect.biblioteca.dto.SolicitareDto;
import com.proiect.biblioteca.mapper.CategorieMapper;
import com.proiect.biblioteca.mapper.SolicitareMapper;
import com.proiect.biblioteca.service.CategorieService;
import com.proiect.biblioteca.service.SolicitareService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SolicitareController.class)
class SolicitareControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SolicitareService solicitareService;

    @MockBean
    private SolicitareMapper solicitareMapper;

/*    public class SolicitareDto {
        private int idUtilizatorAutentificat;
        private int id;
        private int idImprumut;
        private Date termenAmanare;
        private Boolean aprobat;
    }*/

    @Test
    void test_create() throws Exception {
        // Arrange
        SolicitareDto request = SolicitareDto.builder()
                .idImprumut(1)
                .termenAmanare(new Date(2020-12-10))
                .aprobat(false)
                .build();

        Solicitare created = Solicitare.builder()
                .id(10)
                .idImprumut(1)
                .termenAmanare(new Date(2020-12-10))
                .aprobat(false)
                .build();

        when(solicitareService.create(any(Solicitare.class))).thenReturn(created);

        // Act
        mockMvc.perform(post("/solicitari/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        // Assert
    }

    @Test
    void test_getAll() throws Exception {
        // Arrange
        List<Solicitare> returned =  new ArrayList<Solicitare>();
        returned.add(Solicitare.builder()
                .id(10)
                .idImprumut(1)
                .termenAmanare(new Date(2020-12-10))
                .aprobat(false)
                .build());

        when(solicitareService.getAll()).thenReturn(returned);

        // Act
        mockMvc.perform(get("/solicitari")
        )
                .andExpect(status().isOk());
        // Assert
    }
}