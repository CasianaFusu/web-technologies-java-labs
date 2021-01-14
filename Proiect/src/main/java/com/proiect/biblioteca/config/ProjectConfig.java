package com.proiect.biblioteca.config;

import com.proiect.biblioteca.mapper.*;
import com.proiect.biblioteca.mapper.CarteMapperImpl;
import com.proiect.biblioteca.mapper.AutorMapperImpl;
import com.proiect.biblioteca.mapper.CategorieMapperImpl;
import com.proiect.biblioteca.mapper.ImprumutMapperImpl;
import com.proiect.biblioteca.mapper.UtilizatorMapperImpl;
import com.proiect.biblioteca.mapper.SolicitareMapperImpl;
import com.proiect.biblioteca.mapper.RolMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig  {
    @Bean
    public CarteMapper addressMapper() {
        return new CarteMapperImpl();
    }

    @Bean
    public AutorMapper  autorMapper(){
        return new AutorMapperImpl();
    }

    @Bean
    public CategorieMapper categorieMapper() {
        return new CategorieMapperImpl();
    }

    @Bean
    public ImprumutMapper imprumutMapper() {
        return new ImprumutMapperImpl();
    }

    @Bean
    public UtilizatorMapper utilizatorMapper() {
        return new UtilizatorMapperImpl();
    }

    @Bean
    public SolicitareMapper solicitareMapper() {
        return new SolicitareMapperImpl();
    }

    @Bean
    public RolMapper rolMapper() {
        return new RolMapperImpl();
    }
}
