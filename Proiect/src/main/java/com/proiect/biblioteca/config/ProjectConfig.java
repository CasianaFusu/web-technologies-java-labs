package com.proiect.biblioteca.config;

import com.proiect.biblioteca.mapper.AutorMapper;
import com.proiect.biblioteca.mapper.CarteMapper;
import com.proiect.biblioteca.mapper.CarteMapperImpl;
import com.proiect.biblioteca.mapper.AutorMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig {
    @Bean
    public CarteMapper addressMapper() {
        return new CarteMapperImpl();
    }

    @Bean
    public AutorMapper  autorMapper(){
        return new AutorMapperImpl();
    }
}
