package com.example.demo.rest;

import com.example.demo.domain.Shop;
import com.example.demo.dto.ShopDto;
import com.example.demo.mapper.ShopMapper;
import com.example.demo.mapper.ShopMapperImpl;
import com.example.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;
    private final ShopMapper mapper = new ShopMapperImpl();

    @Autowired
    public ShopController(ShopService shopService){
        this.shopService = shopService;
    }

    @PostMapping(path="/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ShopDto> create(@Valid @RequestBody ShopDto request){
        shopService.createShop(mapper.toEntity(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /*@GetMapping("/{cui}")
    ResponseEntity<ShopDto> getByCui(@PathVariable String cui){
        Shop shop = shopService.getShop(cui);
        return new ResponseEntity<>(mapper.toDto(shop),HttpStatus.OK);
    }*/
}
