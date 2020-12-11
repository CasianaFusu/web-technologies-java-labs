package com.example.demo.config;


import com.example.demo.mapper.OwnerMapper;
import com.example.demo.mapper.OwnerMapperImpl;
import com.example.demo.mapper.ShopMapperImpl;
import com.example.demo.mapper.ShopMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public OwnerMapper ownerMapper(){
        return new OwnerMapperImpl();
    }

    @Bean
    public ShopMapper shopMapper(){
        return new ShopMapperImpl();
    }
}
