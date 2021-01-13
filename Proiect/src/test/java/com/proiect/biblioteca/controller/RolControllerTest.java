package com.proiect.biblioteca.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proiect.biblioteca.domain.Categorie;
import com.proiect.biblioteca.domain.Rol;
import com.proiect.biblioteca.dto.CategorieDto;
import com.proiect.biblioteca.dto.RolDto;
import com.proiect.biblioteca.mapper.CategorieMapper;
import com.proiect.biblioteca.mapper.RolMapper;
import com.proiect.biblioteca.service.CategorieService;
import com.proiect.biblioteca.service.RolService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RolController.class)
class RolControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RolService rolService;

    @MockBean
    private RolMapper rolMapper;

    @Test
    void test_create() throws Exception {
        int idUtilizatorAutentificat =1;
        // Arrange
        RolDto request = RolDto.builder()
                .nume(RandomStringUtils.randomAlphabetic(30))
                .build();

        Rol created = Rol.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .build();

        when(rolService.create(any(Rol.class),eq(idUtilizatorAutentificat))).thenReturn(created);

        // Act
        mockMvc.perform(post("/roluri/create/"+idUtilizatorAutentificat)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }
    @Test
    void test_findById() throws Exception {
        // Arrange
        int  request = 10;
        int idUtilizatorAutentificat =1;

        Rol returned = Rol.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .build();

        when(rolService.findById(request,idUtilizatorAutentificat)).thenReturn(returned);

        // Act
        mockMvc.perform(get("/roluri/"+request+"/"+idUtilizatorAutentificat)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }

    void test_getByName() throws Exception {
        // Arrange
        int idUtilizatorAutentificat =1;
        int  request = 10;

        Rol returned = Rol.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .build();

        when(rolService.findById(request,idUtilizatorAutentificat)).thenReturn(returned);

        // Act
        mockMvc.perform(get("/roluri/"+request+"/"+idUtilizatorAutentificat)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }


    @Test
    void test_getAll() throws Exception {
        int idUtilizatorAutentificat =1;
        // Arrange
        List<Rol> returned =  new ArrayList<Rol>();
        returned.add(Rol.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .build());

        when(rolService.getAll(idUtilizatorAutentificat)).thenReturn(returned);

        // Act
        mockMvc.perform(get("/roluri/"+idUtilizatorAutentificat)
        )
                .andExpect(status().isOk());
        // Assert
    }

    @Test
    void test_delete() throws Exception {
        int idUtilizatorAutentificat =1;
        // Arrange
        int  request = 10;
        String expected = "Rolul a fost stears cu succes!";


        when(rolService.delete(request,idUtilizatorAutentificat)).thenReturn(expected);

        // Act
        mockMvc.perform(delete("/roluri/delete/" +request +"/"+idUtilizatorAutentificat)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }

    @Test
    void test_update() throws Exception {
        int idUtilizatorAutentificat =1;
        int numberRowUpdated =1;
        // Arrange
        RolDto request = RolDto.builder()
                .nume(RandomStringUtils.randomAlphabetic(30))
                .build();

        Rol created = Rol.builder()
                .id(11)
                .nume(RandomStringUtils.randomAlphabetic(30))
                .build();

        when(rolService.update(any(Rol.class),eq(idUtilizatorAutentificat))).thenReturn(numberRowUpdated);

        // Act
        mockMvc.perform(put("/roluri/update/"+idUtilizatorAutentificat)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        // Assert
    }

}